import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiRequest } from '../services/api';

const AddNewsPage = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [text, setText] = useState('');
  const [imageIds, setImageIds] = useState([]);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const news = { title, description, text, imageIdList: imageIds };
      await apiRequest('/admin/news', {
        method: 'POST',
        body: JSON.stringify(news),
      });
      navigate('/admin/news'); 
    } catch (error) {
      alert('Error adding news');
    }
  };

  return (
    <div>
      <h2>Add News</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="title">Title</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="description">Description</label>
          <textarea
            id="description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="text">Text</label>
          <textarea
            id="text"
            value={text}
            onChange={(e) => setText(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="images">Images</label>
          <input
            type="text"
            id="images"
            value={imageIds}
            onChange={(e) => setImageIds(e.target.value.split(','))}
            placeholder="Enter image IDs separated by commas"
          />
        </div>
        <button type="submit">Add News</button>
      </form>
    </div>
  );
};

export default AddNewsPage;
