
export const getToken = () => {
    return localStorage.getItem('authToken');
  };
  
  export const setToken = (token) => {
    localStorage.setItem('authToken', token);
  };
  
  export const removeToken = () => {
    localStorage.removeItem('authToken');
  };
  
  export const isAuthenticated = () => {
    const token = getToken();
    return token && !isTokenExpired(token);
  };
  

    export const logout = () => {
        removeToken();
    };
  

  const isTokenExpired = (token) => {
    try {
      const decoded = JSON.parse(atob(token.split('.')[1]));
      const expirationTime = decoded.exp * 1000; 
      return expirationTime < Date.now();
    } catch (error) {
      return true;
    }
  };
  