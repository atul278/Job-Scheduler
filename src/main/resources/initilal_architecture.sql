create TABLE IF NOT EXISTS jobs(
    id serial PRIMARY KEY,
    title VARCHAR (50) UNIQUE NOT NULL,
	cron_expression VARCHAR (50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create TABLE IF NOT EXISTS scheduler_log(
    id serial PRIMARY KEY,
    job_title VARCHAR (50)  NOT NULL,
	status VARCHAR (50) NOT NULL,
    start_time TIMESTAMP ,
    end_time TIMESTAMP
);