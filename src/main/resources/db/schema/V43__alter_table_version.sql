 ALTER TABLE version
  ADD COLUMN version_repository_url VARCHAR(500) NULL;
 ALTER TABLE version
   ADD COLUMN version_repository_branch VARCHAR(50) NULL;
 ALTER TABLE version
   ADD COLUMN version_repository_id VARCHAR(50) NULL;
 ALTER TABLE version
   CHANGE version version_code VARCHAR(20) NOT NULL;
 ALTER TABLE version
   MODIFY COLUMN version_type VARCHAR(20) NOT NULL;
 ALTER TABLE version
   MODIFY COLUMN version_name VARCHAR(50) NOT NULL;
 ALTER TABLE version
   MODIFY COLUMN version_status INT(2) NOT NULL DEFAULT 0;