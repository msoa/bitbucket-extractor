CREATE TABLE repository (
    uuid TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    project_name TEXT NOT NULL,
    create_on TIMESTAMP,
    updated_on TIMESTAMP,
    json TEXT NOT NULL
);

CREATE TABLE pull_request (
    repository_uuid TEXT NOT NULL,
    id INTEGER NOT NULL,
    title TEXT NOT NULL,
    create_on TIMESTAMP,
    updated_on TIMESTAMP,
    json TEXT NOT NULL,
    PRIMARY KEY(repository_uuid, id)
);

CREATE TABLE commit (
    hash TEXT NOT NULL,
    repository_uuid TEXT NOT NULL,
    pull_request_id INTEGER NOT NULL,
    message TEXT NOT NULL,
    date TIMESTAMP,
    json TEXT NOT NULL
);

CREATE TABLE comment (
    id bigserial PRIMARY KEY,
    repository_uuid TEXT NOT NULL,
    pull_request_id INTEGER NOT NULL,
    create_on TIMESTAMP,
    json TEXT NOT NULL
);

CREATE TABLE diff_status (
    hash TEXT PRIMARY KEY,
    repository_uuid TEXT NOT NULL,
    pull_request_id INTEGER NOT NULL,
    "status" TEXT NOT NULL,
    lines_added INTEGER NULL,
    lines_removed INTEGER NULL,
    json TEXT NOT NULL
);

CREATE TABLE activity (
    hash TEXT PRIMARY KEY,
    repository_uuid TEXT NOT NULL,
    pull_request_id INTEGER NOT NULL,
    "date" TIMESTAMP NOT NULL,
    "type" TEXT NOT NULL,
    json TEXT NOT NULL
);

CREATE TABLE log_request (
    id BIGSERIAL PRIMARY KEY,
    create_on TIMESTAMP,
    url TEXT NOT NULL,
    success BOOLEAN NOT NULL,
    error_message TEXT NULL
);

ALTER TABLE pull_request ADD CONSTRAINT id_fk_project FOREIGN KEY(repository_uuid) REFERENCES repository(uuid);
ALTER TABLE commit ADD CONSTRAINT commit_pkey PRIMARY KEY (hash, repository_uuid, pull_request_id);
ALTER TABLE commit ADD CONSTRAINT id_fk_project FOREIGN KEY(repository_uuid, pull_request_id) REFERENCES pull_request(repository_uuid, id);
ALTER TABLE comment ADD CONSTRAINT id_fk_project FOREIGN KEY(repository_uuid, pull_request_id) REFERENCES pull_request(repository_uuid, id);
ALTER TABLE diff_status ADD CONSTRAINT id_fk_diff_status_project FOREIGN KEY(repository_uuid, pull_request_id) REFERENCES pull_request(repository_uuid, id);
ALTER TABLE activity ADD CONSTRAINT id_fk_project FOREIGN KEY(repository_uuid, pull_request_id) REFERENCES pull_request(repository_uuid, id);
CREATE INDEX idx_log_request_url ON log_request(url);