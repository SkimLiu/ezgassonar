doubleClick("1590862594808.png")

type("Via Roma Turin Piemont Italy")
wait(1)

#choose the location from the Checkbox
click(Location(392, 324))
wait(1)

#choose the kind of car sharing
click("1590863686615.png")
wait(1)
click(Location(391, 346))
wait(1)

click("1590863508841.png")
wait(1)
click("1590864001562.png")
wait(1)
doubleClick("1590864475376.png")
type("12")
click("1590864716369.png")
wait(1)

#if add successfully, the img find will approve and print message
result = find("1590864776630.png")
print "Report fuel price successfully, test passed."