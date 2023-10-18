export const Person = function (photo, name, mobile, email,favourite) {
  this.photo = photo;
  this.name = name;
  this.mobile = mobile;
  this.email = email;
  this.favourite = favourite;
};

export const UpdatePerson = function (id,photo, name, mobile, email,favourite) {
  this.id = id;
  this.photo = photo;
  this.name = name;
  this.mobile = mobile;
  this.email = email;
  this.favourite = favourite;
};
