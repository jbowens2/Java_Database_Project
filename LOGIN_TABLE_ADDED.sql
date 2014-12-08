use GSCookieDB;

DROP TABLE IF EXISTS LOGIN;

CREATE TABLE LOGIN(
	USERNAME VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    PRIMARY KEY(USERNAME)
);

INSERT INTO LOGIN(USERNAME, PASSWORD) VALUES ('jimmy@jimmybowens.com','group_project'),('jam.rudolph@gmail.com', 'group_project'), ('shadowrex@aol.com','group_project');
