import React , { useState }from "react";
import {Link} from 'react-router-dom';
import { useDispatch } from "react-redux";
import {createPost} from "../actions/index"
import Navbar from "./Navbar";

const NewPost = () => {
   
    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')
    const [categories, setCategories] = useState('')
    const [likesCount, setLikesCount] = useState(0)

    const onTitleChanged = e => setTitle(e.target.value)
    const onContentChanged = e => setContent(e.target.value)
    const onCategoriesChanged = e => setCategories(e.target.value)

    const dispatch = useDispatch();

    const item = {
        id : new Date().getTime().toString(),
        title : title,
        content : content,
        categories : categories,
        dateCreated : new Date().toDateString(),
        likesCount : likesCount
    }

    const onPublishClicked = (event) => {
        event.preventDefault();
        dispatch(createPost(item));
        setTitle('');
        setContent('');
        setCategories('');
        alert('Blog Post has been created !!!');
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
       <Link to="/">
       <button className="backButton" type="reset">
          Back
        </button>
        </Link>
        <h2 className = "centreHeading">Publish New Post</h2>
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
          Publish
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
  
export default NewPost;
 