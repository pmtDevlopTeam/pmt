 ALTER TABLE remind_content
  ADD COLUMN project_role_id VARCHAR(32) NULL;
 ALTER TABLE remind_content_child
   ADD COLUMN project_role_id VARCHAR(32) NULL;
 