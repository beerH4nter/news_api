import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import MainPage from './pages/MainPage';
import NewsDetailPage from './pages/NewsDetailPage';
import AdminNewsPage from './pages/AdminNewsPage';
import AddNewsPage from './pages/AddNewsPage';
import EditNewsPage from './pages/EditNewsPage';
import AdminImagesPage from './pages/AdminImagesPage';
import AddImagePage from './pages/AddImagePage';
import PrivateRoute from './components/PrivateRoute';
import AdminPage from './pages/AdminPage';
import LoginPage from './pages/LoginPage';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/news" element={<MainPage />} />
          <Route path="/news/:id" element={<NewsDetailPage />} />

  
          <Route
            path="/admin"
            element={
              <PrivateRoute>
                <AdminPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/news"
            element={
              <PrivateRoute>
                <AdminNewsPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/add-news"
            element={
              <PrivateRoute>
                <AddNewsPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/edit-news/:id"
            element={
              <PrivateRoute>
                <EditNewsPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/images"
            element={
              <PrivateRoute>
                <AdminImagesPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/admin/add-image"
            element={
              <PrivateRoute>
                <AddImagePage />
              </PrivateRoute>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
