import React , { useState }from "react";
import {Link,useParams,useNavigate} from 'react-router-dom';
import { useSelector,useDispatch } from "react-redux";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'; 
import {icreamentCount,deletePost} from "../actions/index"
import Navbar from "./Navbar";

const CurrentPost = () => {
    
    const params = useParams();
    const dispatch = useDispatch();
    var currPost = {};
    const blogPosts = Object.values(useSelector((state) => state.blogPostReducer))[0];
    for (var i = 0; i < blogPosts.length; i++) {
        if (blogPosts[i].id === params.id) {
            currPost = blogPosts[i];
            break;
        }
    } 
    const [likesCount, setLikesCount] = useState(currPost.id);

    const item = {id : currPost.id,
        title : currPost.title,
        content : currPost.content,
        categories : currPost.categories,
        dateCreated : currPost.dateCreated,
        likesCount : currPost.likesCount
    }
    const incrementMe = () => {
        setLikesCount(currPost.likesCount);
        dispatch(
            icreamentCount(item)
        );
    }
    const navigate = useNavigate();
    const submit = () => {
        confirmAlert({
          title: "Confirm to submit",
          message: "Are you sure to delete this post",
          buttons: [
            {
              label: "Yes",
              onClick: () => {
                dispatch(deletePost(item))
                navigate('/');
              }
            },
            {
              label: "No"
            }
          ]
        });
      };
    return (
        <>
        <Navbar />
        <div className="content">
        <Link to="/">
       <button className="backButton" type="reset">
          Back
        </button>
        </Link>
        <h2 className = "viewCentreHeading">View Post &nbsp;
        <button className = "like-button" onClick={incrementMe}>❤️ Likes : {currPost.likesCount}</button>
                    </h2>
                    <Link to={`/edit/${currPost.id}`}>
                    <button type="submit" className="editButton">Edit</button>
                    </Link>
                    
                    <button onClick={submit} className="deleteButton">Delete</button>
       <div className="blog-posts pure-g">
        <br></br>
            <div className="pure-u-1 pure-u-md-1-2">
            <article className="blog-post" key={currPost.id}>
                    <h2 className="blog-post-title">{currPost.title}</h2>
                    <p className="blog-post-date">{currPost.dateCreated}</p>
                    <p className="blog-post-content">
                        {currPost.content}
                    </p>
                </article>
            </div>
        </div>
       </div>
        </>
      );
};
  
export default CurrentPost;
 