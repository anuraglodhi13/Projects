import React  from "react";
import { useLocation ,Link  } from 'react-router-dom';

const Navbar = (props) => {
  const location = useLocation();
    return (
      <>
       <nav className="navbar navbar-dark navbar-expand-sm bg-dark fixed-top">
        <div className="container">
        <a href="/" className="navbar-brand">
        Blog Posts
        </a>
        {location.pathname ==="/" && 
        <Link to="/new">
          <button 
              className="btn my-2 my-sm-0" 
              type="submit"
              >New Post</button>
        </Link>
              
            }
        


    </div>
    </nav>
      </>
    );
  };
  
export default Navbar;
 