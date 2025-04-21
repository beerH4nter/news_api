export const getAuthToken = () => localStorage.getItem('authToken');

export const apiRequest = async (url, options = {}) => {
  const token = getAuthToken();
  
  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
    ...options.headers,
  };

  const response = await fetch(url, {
    ...options,
    headers,
  });

  if (response.status === 401) {
    window.location.href = '/login';  // Если не авторизован
  }

  return response.json();
};
