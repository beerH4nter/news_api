import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { apiRequest } from '../services/api';

const AdminPage = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await apiRequest('/admin/users');
        setUsers(data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    };
    
    fetchUsers();
  }, []);

  const handleDelete = async (userId) => {
    try {
      await apiRequest(`/admin/users/${userId}`, { method: 'DELETE' });
      setUsers(users.filter(user => user.id !== userId));
    } catch (error) {
      alert('Error deleting user');
    }
  };

  return (
    <div>
      <h2>Admin Panel</h2>
      <h3>User List</h3>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {user.username} - {user.role}
            <Link to={`/admin/edit-user/${user.id}`}>
              <button>Edit</button>
            </Link>
            <button onClick={() => handleDelete(user.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <Link to="/admin/add-user">
        <button>Add User</button>
      </Link>
    </div>
  );
};

export default AdminPage;
