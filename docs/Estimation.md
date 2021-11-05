# Project Estimation  

Authors: Michele Filippini, Giovanni Brignone, Andrea Zappavigna, Dong Liu

Date: 18/06/2020

Version: 2

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| NC =  Estimated number of classes to be developed   |        30                  |             
|  A = Estimated average size per class, in LOC       |         75                   | 
| S = Estimated size of project, in LOC (= NC * A) | 2250 |
| E = Estimated effort, in person hours (considering productivity computed in DashBoard, 11 LOC per person hour)  |  205  |   
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 6150 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 1.28  |               


# Estimate by activity decomposition



### 

| Activity name    		| Estimated effort (person hours) |
| :-----------: 		| :-----------------------------: | 
| Requirement engineering	| 30	|
| Requirement inspection	| 10	|
| Architecture and design	| 20	|
| Design inspection		| 5	|
| Implementation		| 55	|
| Code inspection 		| 20	|
| Test 				| 35	|
| Project management 		| 15	|
| Configuration management 	| 15	|


###

```plantuml
Project starts the 2020/04/8
[Requirement engineering & inspection] as [RE] lasts 2 days
[RE] is colored in lightblue/blue
[Requirement document] happens at [RE]'s end
[Architecture and design & inspection] as [AD] lasts 1 days
[AD] starts at [RE]'s end
[AD] is colored in fuchsia/firebrick
[Design document] happens at [AD]'s end
[Implementation] as [I] lasts 3 days
[I] starts at [AD]'s end
[Code inspection] as [CI] lasts 1 days
[CI] starts at [I]'s end
[Test] as [T] lasts 2 days
[T] starts at [I]'s end
[Executable] happens at [T]'s end
[Project management] as [PM] starts at [RE]'s start and ends at [T]'s end
[Configuration management] as [CM] starts at [RE]'s start and ends at [T]'s end
[PM] is colored in yellow/red
[CM] is colored in yellow/red
```
