-- Drop user first if they exist
DROP USER if exists 'springroot'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springroot'@'%' IDENTIFIED BY 'springpassword';

GRANT ALL PRIVILEGES ON * . * TO 'springroot'@'%';