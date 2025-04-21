import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { apiRequest } from '../services/api';

const EditNewsPage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [news, setNews] = useState({ title: '', description: '', text: '', imageIdList: [] });

  useEffect(() => {
    const fetchNews = async () => {
      try {
        const data = await apiRequest(`/admin/news/${id}`);
        setNews(data);
      } catch (error) {
        alert('Error fetching news');
      }
    };
    
    fetchNews();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await apiRequest(`/admin/news/${id}`, {
        method: 'PUT',
        body: JSON.stringify(news),
      });
      navigate('/admin/news'); 
    } catch (error) {
      alert('Error updating news');
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNews((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <div>
      <h2>Edit News</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title</label>
          <input
            type="text"
            id="title"
            name="title"
            value={news.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            name="description"
            value={news.description}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="text">Text</label>
          <textarea
            id="text"
            name="text"
            value={news.text}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="imageIds">Images</label>
          <input
            type="text"
            id="imageIds"
            name="imageIdList"
            value={news.imageIdList.join(',')}
            onChange={(e) => setNews((prevState) => ({ ...prevState, imageIdList: e.target.value.split(',') }))}
            placeholder="Enter image IDs separated by commas"
          />
        </div>
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
};

export default EditNewsPage;
