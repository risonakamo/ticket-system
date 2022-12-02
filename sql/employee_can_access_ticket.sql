-- query to check if a certain employee is able to access a certain ticket. they are able
-- to access it if their id matches the creator id or is in the assigned id array

select id from ticket2
where
    (
        creator_id = 'd8626223-f3d8-4458-bf31-2f3aa831cc06'
        or 'd8626223-f3d8-4458-bf31-2f3aa831cc06' = any(ticket2.assigned_employees)
    )
    and id = '1fc680c8-98ca-4db9-8a7a-5b3d79abb778'





-- jpa arguments
select id from ticket2
where
    (
        creator_id = :employeeId
        or :employeeId = any(ticket2.assigned_employees)
    )
    and id = :ticketId