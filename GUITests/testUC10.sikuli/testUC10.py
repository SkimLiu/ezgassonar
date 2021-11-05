#the orginal reputation of user usertest is 3, will test increase, modify on gas station intest
#the orginal reputation of user contest is 1, will test decrease, modify on gas station detest

#step1: U selects gas station G
doubleClick("1590895456087.png")
type("via roma")
wait(1)
click(Location(386, 992))
#the location deponds on your browser
wait(1)
click("1590895521304.png")
wait(1)
type(Key.PAGE_DOWN)
wait(1)

#test increase and decrease together

if find("1590895806057.png"):
    click("1590895860610.png")
else:
    pass

if find("1590895910407.png"):
    click("1590895922558.png")
else:
    pass

wait(1)

#login in admin page and check the reputation
type(Key.PAGE_UP)
wait(1)
click("1590896074208.png")
wait(1)
click("1590896093812.png")
wait(1)
doubleClick("1590896115806.png")
type("admin@ezgas.com")
wait(1)
doubleClick("1590896152090.png")
type("admin")
wait(1)
click("1590896690733.png")
wait(3)

click("1590896195630.png")
wait(1)

if find("1590896321427.png"):
    print "increase!"
else:
    print "failed increase"
if find("1590896377702.png"):
    print "decrease!"
else:
    print "failed decrease"
print "test passed"
    
