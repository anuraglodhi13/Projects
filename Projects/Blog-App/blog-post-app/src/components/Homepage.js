import React from "react";
import Navbar from "./Navbar";
import { useSelector } from "react-redux";
import {Link} from 'react-router-dom';

const Homepage = () => {
    const blogPosts = Object.values(useSelector((state) => state.blogPostReducer))[0];
  
    return (
      <>
      <Navbar />
      { 
                blogPosts.map((post) => {
                    var txt = post.content;
                    if(txt.length > 10)
                    {
                      txt = txt.substr(0,120) + "...";
                    }
                    return (
                        <Link to={`/${post.id}`}>
      <div className="content" >
       <div className="blog-posts pure-g" >
            <div className="pure-u-1 pure-u-md-1-2" key={post.id}>
            <article className="blog-post" >
                    <h2 className="blog-post-title">{post.title}</h2>
                    <p className="blog-post-date">{post.dateCreated}</p>
                    <p className="blog-post-content">
                        {txt}
                    </p>
                </article>
            </div>
        </div>
       </div>
      </Link>
                    );
                })
            }

      
      </>
    );
  };
  
export default Homepage;
 