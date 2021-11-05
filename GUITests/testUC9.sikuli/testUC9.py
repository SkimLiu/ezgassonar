#prerequisites: The fuel price is attached.

#list the gas stations
click("1590874987750.png")
wait(1)

#the original trust is 60
click("1590875096516.png")
click("1590875174776.png")
wait(1)
increase = find("1590875152705.png")
print "Successfully increase trust"

click("1590875226279.png")
click("1590875240703.png")
wait(1)
decrease = find("1590875274527.png")
print "Successfully decrease trust"
print "test passed"

