// components/Navbar.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import { logout } from '../utils/auth';
import '../styles/Navbar.css';

const Navbar = () => {
  const handleLogout = () => {
    logout();
    window.location.href = '/login'; 
  };

  return (
    <nav className="navbar">
      <Link to="/">Главная</Link>
      <Link to="/news">Новости</Link>
      <Link to="/admin">Админка</Link>
      <button onClick={handleLogout}>Выйти</button>
    </nav>
  );
};

export default Navbar;
