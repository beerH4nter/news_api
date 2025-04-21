import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { apiRequest } from '../services/api';

const AdminImagesPage = () => {
  const [images, setImages] = useState([]);

  useEffect(() => {
    const fetchImages = async () => {
      try {
        const data = await apiRequest('/admin/images');
        setImages(data);
      } catch (error) {
        console.error('Error fetching images:', error);
      }
    };
    
    fetchImages();
  }, []);

  const handleDelete = async (imageId) => {
    try {
      await apiRequest(`/admin/images/${imageId}`, { method: 'DELETE' });
      setImages(images.filter(image => image.id !== imageId));
    } catch (error) {
      alert('Error deleting image');
    }
  };

  return (
    <div>
      <h2>Images Management</h2>
      <h3>Images List</h3>
      <ul>
        {images.map((image) => (
          <li key={image.id}>
            <img src={`http://localhost:8080${image.image}`} alt="image" width="100" />
            <button onClick={() => handleDelete(image.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <Link to="/admin/add-image">
        <button>Add Image</button>
      </Link>
    </div>
  );
};

export default AdminImagesPage;
