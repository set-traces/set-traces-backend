

-- Adding script id to playing roles

ALTER TABLE playing_roles ADD COLUMN script_id uuid;
ALTER TABLE playing_roles ADD CONSTRAINT fk_script_playing_roles_script_id FOREIGN KEY (script_id) REFERENCES scripts(id);