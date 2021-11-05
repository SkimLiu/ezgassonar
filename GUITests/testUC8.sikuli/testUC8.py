#select a GP and r
doubleClick("1590880488470.png")
type("Porta Susa Turin Piemont Italy")
wait(1)
click(Location(449, 837))
click("1590879705377.png")
wait(1)
doubleClick("1590880539008.png")
wait(1)
#scroll down to the buttom
type(Key.PAGE_DOWN)
wait(1)
#Variants1: restrict a certain fuel type
click("1590881079799.png")
wait(1)
click(Location(426, 516))
wait(1)
click("1590881109576.png")
wait(1)
print "shows only prices for a certain fuel type"

#Variants2: restrict to car sharing
click("1590881719814.png")
wait(1)
click(Location(365, 714))
wait(1)
click("1590881814409.png")
wait(1)
print "shows only gas stations having a deal with a certain car sharing company"

#Variants3: sort by price, for a fuel type
click("1590882131816.png")
wait(1)
click(Location(357, 757))
wait(1)
click("1590882185934.png")
wait(1)
click(Location(354, 708))
wait(1)
click("1590882248074.png")
wait(1)
click(Location(299, 991))
wait(1)
click(Location(374, 990))
print "shows only gas stations for a certain fuel type, sorted by price, in ascending order"

