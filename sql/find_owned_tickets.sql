-- find all tickets "owned" by some uid. a ticket is considered owned if the ticket was created
-- by the uid or assigned to the uid

select * from ticket2
where
    creator_id = '9c3c86ec-ec8a-4640-a7c3-6ceea40e37cd'
    or 'e3702c31-7fd8-4acc-bc78-66736abc2059'=any(ticket2.assigned_employees)