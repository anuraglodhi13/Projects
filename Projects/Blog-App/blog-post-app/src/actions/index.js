export const createPost = (item) => {
    return {
        type:"ADDPOST",
        payload: item
    }
}

export const icreamentCount = (item) => {
    return {
        type:"INCREAMENTLIKES",
        payload: item
    }
}

export const editPost = (item) => {
    return {
        type:"EDITPOST",
        payload: item
    }
}

export const deletePost = (item) => {
    return {
        type:"DELETEPOST",
        payload: item
    }
}