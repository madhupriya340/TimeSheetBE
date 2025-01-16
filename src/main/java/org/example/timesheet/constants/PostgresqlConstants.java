package org.example.timesheet.constants;

public class PostgresqlConstants {
    public static final class RoleConstants{

        public static final String Add_Role = "insert into roles(role_name) values(?)";

        public static final String GET_ALL_Roles = "select * from roles";

    }
    public static final class UserConstants{

        public static final String Add_User = "insert into users(email,password,is_active,username,created_at,updated_at) values(?,?,true,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";

        public static final String GET_USER_BY_EMAIL = "select * from users where email=? and is_active=true";

        public static final String GET_ALL_Users = "select u.user_id,u.username,string_agg(r.role_name,',' ) as role_name" +
                                                    " from users u " +
                                                    " left join user_roles ur on (u.user_id=ur.user_id) " +
                                                    " join roles r on ur.role_id = r.role_id " +
                                                    " where is_active=true " +
                                                    " group by u.user_id,u.username";

        public static final String GET_USERS_BY_TYPE = "select u.user_id,u.username,r.role_name from users u " +
                                                        " join roles r on ( u.role_id=r.role_id) " +
                                                        " where lower(r.role_name)=? and is_active=true";

        public static final String UPDATE_USER = "update users set username=? where user_id=?";

        public static final String ADD_ROLES="insert into user_roles(user_id,role_id) values(?,?)";

        public static final String DELETE_USER="update user set is_active=false where user_id=?";
    }
    public static final class ProjectConstants{

        public static final String ADD_PROJECT = "INSERT INTO projects (project_code, project_name, project_desc, business_impact, start_date, end_date,project_status, created_at)" +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        public static final String ADD_PROJECT_CONSULTANT = "INSERT INTO project_consultants (project_id, consultant_id, created_at)" +
                "VALUES (?, ?, CURRENT_TIMESTAMP)";

        public static final String ADD_PROJECT_MANAGER = "INSERT INTO project_managers (project_id, project_manager_id, created_at)" +
                "VALUES (?, ?, CURRENT_TIMESTAMP)";

        public static final String GET_Project_By_ID = "select * from projects where project_id= ?";

        public static final String GET_ALL_PROJECTS = "select p.project_id,p.project_code,p.project_name,p.project_desc,p.start_date,p.end_date,p.project_status,u.username as project_manager_name from projects p " +
                                                        " join project_managers m on (p.project_id=m.project_id) " +
                                                        " join users u on (m.project_manager_id=u.user_id) " +
                                                        " group by p.project_id,p.project_code,p.project_name,p.project_desc,p.start_date,p.end_date,p.project_status,u.username ";

        public static final String GET_PROJECT_CONSULTANTS = "SELECT consultant_id FROM project_consultants WHERE project_id = ?";

        public static final String GET_PROJECT_MANAGERS = "SELECT project_manager_id FROM project_managers WHERE project_id = ?";

        public static final String GET_PROJECT_BY_CODE="select * from projects where project_code=?";

        public static final String UPDATE_PROJECT_BY_CODE="update projects set project_name=coalesce(?,a.project_name) , project_desc=coalesce(?,a.project_desc) , business_impact= coalesce(?,a.business_impact) , project_status = coalesce(?,a.project_status) From projects a  where a.project_code=? and a.project_id=projects.project_id ";

        public static final String UPDATE_PROJECT_CONSULTANTS = "update project_consultants set consultant_id= ? FROM project_consultants WHERE project_code = ?";

        public static final String UPDATE_PROJECT_MANAGERS = "update project_managers set project_manager_id= ? FROM project_managers WHERE project_code = ?";

        public static final String DELETE_PROJECT="delete from projects where project_code=? and lower(project_status)='rejected'";

        public static final String DELETE_PROJECT_CONSULTANTS = "delete FROM project_consultants WHERE project_id = ?";

        public static final String DELETE_PROJECT_MANAGERS = "delete FROM project_managers WHERE project_id = ?";

    }
    public static final class TaskConstants{

        public static final String ADD_WEEKLY_TASK="Insert into weekly_tasks(week_start_dt,project_id,consultant_id) values(?,?,?)";

        public static final String FIND_WEEKLY_TASK_BY_ID="select count(distinct week_id) as cnt from weekly_tasks where week_start_dt=? and project_id=? and consultant_id=?";

        public static final String GET_WEEKLY_TASK_BY_ID="select * from weekly_tasks where week_start_dt=? and project_id=? and consultant_id=?";

        public static final String ADD_Task="INSERT INTO tasks (week_id,task_code,task_title, task_desc, task_date, worked_hrs, created_dt, updated_dt) " +
                                            " VALUES (?,('PRJ'|| ? ||'-'||LPAD(nextval('task_sequence')::TEXT, 3, '0')),?, ?, ?, ?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

        public static final String UPDATE_TASK_BY_ID="update tasks set task_title=coalesce(?,a.task_title), task_desc=coalesce(?,a.task_desc), worked_hrs=coalesce(?,a.worked_hrs) from tasks a where a.task_id=tasks.task_id and a.task_id=?";

        public static final String GET_Tasks_BY_ID = "select * from tasks where consultant_id= ? and project_id= ? and (EXTRACT(DOW FROM ?::date)=1::numeric and task_date>=? and task_date <= (?::timestamp + INTERVAL '4 days'))";

        public static final String GET_TASKS_HOURS_SUMMARY="SELECT task_code,task_title FROM tasks " +
                                                "WHERE task_dt >= ? " +
                                                "AND task_dt < ? + INTERVAL '7 days'";

        public static final String ADD_COMMENTS="insert into comments_transaction(week_id,comment,commented_by,created_at) values(?,?,?,CURRENT_TIMESTAMP)";

        public static  final String DELETE_TASK="delete from tasks where task_id=? ";

        public static final String GET_DAY_WISE_TASKS_BY_WEEKLY_SBMT_DT="select extract('day' from task_dt) as day,t.task_title,worked_hrs from weekly_tasks w " +
                                                                            " left join tasks t on (w.week_id=t.week_id) " +
                                                                            " where submitted_date=? ";

        public static final String GET_MNTH_WISE_TASKS_BY_WEEKLY_SBMT_DT="select extract('month' from task_dt) as mnth,w.submitted_date,sum(coalesce(t.worked_hrs)) as wrkd_hrs from weekly_tasks w " +
                                                                        " left join tasks t on (w.week_id=t.week_id) " +
                                                                        "  group by w.submitted_date";
    }
}
