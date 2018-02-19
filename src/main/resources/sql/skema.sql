create table category(


   idcategory int auto_increment,
   name varchar(255) not null,
   description varchar(255) not null,
   valid boolean default true,

   CONSTRAINT pk_category PRIMARY KEY (idcategory)
);