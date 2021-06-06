create TABLE IF NOT EXISTS ums.jobs(
        id serial PRIMARY KEY,
        title VARCHAR (50) UNIQUE NOT NULL,
		cron_expression VARCHAR (50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

drop table ums.jobs;
drop table ums.scheduler_log
  create TABLE IF NOT EXISTS ums.scheduler_log(
        id serial PRIMARY KEY,
        job_title VARCHAR (50)  NOT NULL,
		status VARCHAR (50) NOT NULL,
    start_time TIMESTAMP ,
    end_time TIMESTAMP

);