

-- 编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。
--
-- +----+--------+
-- | Id | Salary |
-- +----+--------+
-- | 1  | 100    |
-- | 2  | 200    |
-- | 3  | 300    |
-- +----+--------+
--
-- 来源：力扣（LeetCode）
-- 链接：https://leetcode-cn.com/problems/second-highest-salary
-- 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


select IFNULL((select  distinct  salary from Employee order  by salary desc  limit 1 offset  1),null ) as SecondHighestSalary;

