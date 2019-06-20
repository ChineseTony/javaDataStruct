/*
Leetcode 182
编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。

示例：
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
*/
select Email  from person group by Email having count(Email) > 1

/*
595. 大的国家
 */
 select name,population,area from world where area > 3000000 or population > 25000000

/**
602有趣的电影
 */
select id,movie,description,rating from cinema
where  description != 'boring' and id%2 != 0 order by rating desc
/**
175交换工资
 */
update salary set sex = if(sex = 'f', 'm','f');

/*
181.超过经理收入的员工
 */

select
a.Name as Employee
from Employee a join Employee b  on a.ManagerId = b.Id and a.Salary > b.Salary
select a.name as Employee from Employee a where a.salary >
(select b.salary from Employee b where b.id = a.ManagerId  )

/***
196编写一个 SQL 查询，
来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。
 */

delete from Person where Id not in
(select Id from ( select min(Id) Id,Email from Person group by Email)t)