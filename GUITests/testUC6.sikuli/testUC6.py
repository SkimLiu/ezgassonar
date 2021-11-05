# UC6-Delete Gas Station
# Add a test station named "guitest", ID is the primary key
# Click the button remove
# If delete successfully, the second find named "after" will give a findfailed error.

before = find("1590861180555.png")
print before
#It will print value of M
#Or print "the gas station is exist"
wait(1)

doubleClick("1590859892868.png")

wait(1)
after = find("1590861263779.png")
print after
#The error represents the process works
