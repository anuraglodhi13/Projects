const initialState = {
    posts : []
};

export const blogPostReducer = (state = initialState, action) => {
    
    if(action.type === "ADDPOST") {
        const {id} = action.payload;
    const existingPost = state.posts.find((post) => post.id === id);
        if(!existingPost) {
            state.posts.push(...[action.payload]);
        }
    }
    else if (action.type === "INCREAMENTLIKES") {
        const {id} = action.payload;
    const existingPost = state.posts.find((post) => post.id === id);
        if(existingPost) {
            existingPost.likesCount = existingPost.likesCount+1;
        }
    }
    else if (action.type === "EDITPOST") {
        const {id,title,content,categories} = action.payload;
    const existingPost = state.posts.find((post) => post.id === id);
        if(existingPost) {
            existingPost.title = title;
            existingPost.content = content;
            existingPost.categories = categories;
        }
    }
    else if (action.type === "DELETEPOST") {
        const {id} = action.payload;
        console.log(action.payload);
    const existingPost = state.posts.find((post) => post.id === id);
        if(existingPost) {
            state.posts = state.posts.filter((post) => post.id !== id);
        }
    }
    return state;
}

