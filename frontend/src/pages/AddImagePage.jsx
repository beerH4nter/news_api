import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { apiRequest } from '../services/api';

const AddImagePage = () => {
  const [image, setImage] = useState(null);
  const navigate = useNavigate();

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('image', image);

    try {
      await apiRequest('/admin/images', {
        method: 'POST',
        body: formData,
      });
      navigate('/admin/images');
    } catch (error) {
      alert('Error adding image');
    }
  };

  return (
    <div>
      <h2>Add Image</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="image">Image</label>
          <input
            type="file"
            id="image"
            onChange={handleImageChange}
            required
          />
        </div>
        <button type="submit">Add Image</button>
      </form>
    </div>
  );
};

export default AddImagePage;
