import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <ProductsMain />,
      },
      {
        path: "/products",
        element: <ProductsMain />,
      },
      {
        path: "/about",
        element: <AboutMain />,
      },
      {
        path: "/contact",
        element: <ContactMain />,
      },
      {
        path: "/login",
        element: <LoginMain />,
      },
      {
        path: "/register",
        element: <RegisterMain />,
      },
      {
        path: "/cart",
        element: <CartMain />,
      },
    ]
  },
]);


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)