

-- Adding script id ot lines

ALTER TABLE lines ADD COLUMN script_id uuid;
ALTER TABLE lines ADD CONSTRAINT fk_script_line_script_id FOREIGN KEY (script_id) REFERENCES scripts(id);