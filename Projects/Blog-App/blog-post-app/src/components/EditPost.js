import React , { useState }from "react";
import {Link,useParams,useNavigate} from 'react-router-dom';
import { useDispatch,useSelector } from "react-redux";
import {editPost} from "../actions/index"
import Navbar from "./Navbar";

const EditPost = () => {
    const params = useParams();
    var currPost = {};
    const blogPosts = Object.values(useSelector((state) => state.blogPostReducer))[0];
    for (var i = 0; i < blogPosts.length; i++) {
        if (blogPosts[i].id === params.id) {
            currPost = blogPosts[i];
            break;
        }
    } 
    const [title, setTitle] = useState(currPost.title);
    const [content, setContent] = useState(currPost.content);
    const [categories, setCategories] = useState(currPost.categories);

    const onTitleChanged = e => setTitle(e.target.value)
    const onContentChanged = e => setContent(e.target.value)
    const onCategoriesChanged = e => setCategories(e.target.value)

    const dispatch = useDispatch();
    const navigate = useNavigate();
        const item = {id : currPost.id,
            title : title,
            content : content,
            categories : categories,
            dateCreated : currPost.dateCreated,
            likesCount : currPost.likesCount
        }

    const onPublishClicked = (event) => {
        event.preventDefault();
        dispatch(editPost(item));
        setTitle('');
        setContent('');
        setCategories('');
        alert('Blog Post has been edited !!!');
        navigate(`/${currPost.id}`);
    }

    const onCancelClicked = (event) => {
        event.preventDefault();
        setTitle('');
        setContent('');
        setCategories('');
    }

    return (
      <>
      <Navbar />
       <div className="content">
       <Link to={`/${currPost.id}`}>
       <button className="backButton" type="reset">
          Back
        </button>
        </Link>
        <h2 className = "centreHeading">Edit Your Post</h2>
       <div className="write">
      <form className="writeForm">
        <div className="writeFormGroup">
            <label  className="writeInput">Title :</label>
          <input
            className="writeInput"
            placeholder="Title"
            type="text"
            onChange={onTitleChanged}
            value = {title}
          />
        </div>
        <div className="writeFormGroup">
        <label  className="writeInput">Categories :</label>
          <input
            className="writeInput"
            placeholder="Categories"
            type="text"
            onChange={onCategoriesChanged}
            value={categories}
          />
        </div>
        <div className="writeFormGroup">
        <label  className="writeInput">Content :</label>
          <textarea
            className="writeInput writeText"
            placeholder="Content For Your Post"
            type="text"
            onChange={onContentChanged}
            value = {content}
          />
        </div>
        <button className="writeSubmit"  type="submit" onClick={onPublishClicked}>
          Edit
        </button>
        <button className="writeCancel" type="reset" onClick={onCancelClicked}>
          Cancel
        </button>
      </form>
    </div>
       </div>
      </>
    );
};
  
export default EditPost;
 