CREATE TABLE IF NOT EXISTS testers
(
    id         BIGINT PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name  VARCHAR(64) NOT NULL,
    country    VARCHAR(64) NOT NULL,
    last_login DATETIME    NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS devices
(
    id          BIGINT PRIMARY KEY,
    description VARCHAR(64) NOT NULL UNIQUE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS tester_devices
(
    tester_id BIGINT,
    device_id BIGINT,
    PRIMARY KEY (tester_id, device_id),
    FOREIGN KEY (tester_id) REFERENCES testers (id),
    FOREIGN KEY (device_id) REFERENCES devices (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS bug_reports
(
    id        BIGINT PRIMARY KEY,
    device_id BIGINT,
    tester_id BIGINT,
    FOREIGN KEY (device_id) REFERENCES devices (id),
    FOREIGN KEY (tester_id) REFERENCES testers (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
