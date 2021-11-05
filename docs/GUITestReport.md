# GUI  Testing Documentation

Authors: Michele Filippini, Giovanni Brignone, Andrea Zappavigna, Dong Liu

Date: 29/05/2020

Version: 1 (REST API Testing)
	 2 (GUI Testing)

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

Each test is in an unique sikuli folder in ezgas/GUITests

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- |
| 1	| FR1.1	| `testUC1.sikuli/testUC1.py` |
| 2	| FR1.1	| `testUC2.sikuli/testUC2.py` |
| 3	| FR1.2	| `testUC3.sikuli/testUC3.py` |
| 4	| FR3.1	| `testUC4.sikuli/testUC4.py` |
| 5	| FR3.1	| `testUC5.sikuli/testUC5.py` |
| 6	| FR3.2	| `testUC6.sikuli/testUC6.py` |
| 7	| FR5.1	| `testUC7.sikuli/testUC7.py` |
| 8	| FR4.1, FR4.3, FR4.4, FR4.5 | `testUC8.sikuli/testUC8.py` |
| 9	| FR5.2	| `testUC9.sikuli/testUC9.py` |
| 10.1	| FR5.2, FR5.3 | `testUC10.sikuli/testUC10.py` |          
| 10.2	| FR5.2, FR5.3 | `testUC10.sikuli/testUC10.py` |            


# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end.
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name | Functional Requirements covered |REST  API Test(s) |
| ----------- | ------------------------------- | ----------- |
| `UserController.getUserById` | FR1.4 | `TestController.testGetUserById` |     
| `UserController.getAllUsers` | FR1.3 | `TestController.testGetAllUsers` |             
| `UserController.saveUser` | FR1.1 | `TestController.testSaveUser` |   
| `UserController.deleteUser` | FR1.2 | `TestController.testDeleteUser` |   
| `UserController.increaseUserReputation` | FR5.3, FR5.2 | `TestController.testIncreaseUserReputation` |   
| `UserController.decreaseUserReputation` | FR5.3, FR5.2 | `TestController.testDecreaseUserReputation` |
| `UserController.login` | FR2 | `TestController.testLogin` |   
| `GasStationController.getGasStationById` | FR4, FR5.2 | `TestController.testGetGasStationById` |   
| `GasStationController.getAllGasStations` | FR3.3, FR5.2 | `TestController.testGetAllGasStations` |   
| `GasStationController.saveGasStation` | FR3.1 | `TestController.testSaveGasStation` |   
| `GasStationController.deleteUser` | FR3.2 | `TestController.testDeleteGasStation` |
| `GasStationController.getGasStationByGasolineType` | FR4.4, FR5.2 | `TestController.testGetGasStationByGasolineType` |  
| `GasStationController.getGasStationByProximity` | FR4.1, FR5.2 | `TestController.testGetGasStationByProximity` |   
| `GasStationController.getGasStationWithCoordinates` | FR4.1, FR4.4, FR4.5, FR5.2 | `TestController.testGetGasStationWithCoordinates` |
| `GasStationController.setGasStationReport` | FR5.1, FR5.2 | `TestController.testSetGasStationReport` |
