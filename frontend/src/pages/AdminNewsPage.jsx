import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { apiRequest } from '../services/api';

const AdminNewsPage = () => {
  const [newsList, setNewsList] = useState([]);

  useEffect(() => {
    const fetchNews = async () => {
      try {
        const data = await apiRequest('/admin/news');
        setNewsList(data);
      } catch (error) {
        console.error('Error fetching news:', error);
      }
    };
    
    fetchNews();
  }, []);

  const handleDelete = async (newsId) => {
    try {
      await apiRequest(`/admin/news/${newsId}`, { method: 'DELETE' });
      setNewsList(newsList.filter(news => news.id !== newsId));
    } catch (error) {
      alert('Error deleting news');
    }
  };

  return (
    <div>
      <h2>News Management</h2>
      <h3>News List</h3>
      <ul>
        {newsList.map((news) => (
          <li key={news.id}>
            {news.title}
            <Link to={`/admin/edit-news/${news.id}`}>
              <button>Edit</button>
            </Link>
            <button onClick={() => handleDelete(news.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <Link to="/admin/add-news">
        <button>Add News</button>
      </Link>
    </div>
  );
};

export default AdminNewsPage;
