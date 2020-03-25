

-- Adding role to lines

ALTER TABLE lines ADD COLUMN prole_id uuid;
ALTER TABLE lines ADD CONSTRAINT fk_playing_role_lines_prole_id FOREIGN KEY (prole_id) REFERENCES playing_roles(id);