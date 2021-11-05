# Integration and API Test Documentation

Authors: Brignone Giovanni, Filippini Michele, Zappavigna Andrea, Dong Liu

Date: 21/05/2020

Version: 1

# Contents

- [Dependency graph](#dependency-graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph

```plantuml
package Frontend {}
package Backend {
	package "it.polito.ezgas.controller" {
		class HomeController
		class UserController
		class GasStationController
	}

	package "it.polito.ezgas.service" {
		interface UserService
		interface GasStationService

		package "it.polito.ezgas.service.impl" {
			class UserServiceImpl
			class GasStationServiceImpl
		}
	}

	package "it.polito.ezgas.dto" {
		class GasStationDto
		class IdPw
		class LoginDto
		class UserDto
		class PriceReportDto
	}

	package "it.polito.ezgas.converter" {
		class UserConverter
		class GasStationConverter
	}

	package "it.polito.ezgas.repository" {
		class GasStationRepository
		class UserRepository
	}

	package "it.polito.ezgas.entity" {
		class GasStation
		class User
	}

	GasStationController --> GasStationService
	GasStationController --> PriceReportDto
	
	UserController --> UserService

	GasStationService <|.. GasStationServiceImpl
	UserService <|.. UserServiceImpl

	GasStationServiceImpl --> GasStationConverter
	GasStationServiceImpl --> GasStationRepository
	GasStationServiceImpl --> UserRepository
	GasStationServiceImpl --> User
	GasStationServiceImpl --> GasStation
	GasStationServiceImpl --> GasStationDto

    	UserServiceImpl --> UserRepository
    	UserServiceImpl --> LoginDto
    	UserServiceImpl --> IdPw
    	UserServiceImpl --> UserDto
    	UserServiceImpl --> UserConverter
    	UserServiceImpl --> User

	GasStationConverter --> GasStationDto
	GasStationConverter --> UserConverter
	GasStationConverter --> GasStation
	
	UserConverter --> UserDto
	UserConverter --> User

	GasStationRepository --> GasStation
	UserRepository --> User
	
}

Frontend --> Backend
```

# Integration approach
The bottom-up technique has been choosen as integration sequence.
Classes under test have been added according to the following steps:

| Step \# | Package(s) | Class(es) |
| - | - | - |
| 0 | it.polito.ezgas.entity | GasStation <br> User |
| | it.polito.ezgas.dto | GasStationDto <br> IdPw <br> LoginDto <br> UserDto <br> PriceReportDto |
| 1 | it.polito.ezgas.repository | GasStationRepository <br> UserRepository |
| | it.polito.ezgas.converter | UserConverter |
| 2 | it.polito.ezgas.service.impl | UserServiceimpl |
| | it.polito.ezgas.converter | GasStationConverter |
| 3 | it.polito.ezgas.service.impl | GasStationServiceImpl |

N.B. step 0 is the starting point, obtained by unit testing.

#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1

### Package: `it.polito.ezgas.repository`
#### Class: `GasStationRepository`
##### Method: `findByHasDieselTrueOrderByDieselPrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasDieselTrueOrderByDieselPrice`|

##### Method: `findByHasPremiumDieselTrueOrderByDieselPrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasPremiumDieselTrueOrderByPremiumDieselPrice`|

##### Method: `findByHasGasTrueOrderByGasPrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasGasTrueOrderByGasPrice`|

##### Method: `findByHasMethaneTrueOrderByMethanePrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasMethaneTrueOrderByMethanePrice`|

##### Method: `findByHasSuperTrueOrderBySuperPrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasSuperTrueOrderBySuperPrice`|

##### Method: `findByHasSuperPlusTrueOrderBySuperPlusPrice`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByHasSuperPlusTrueOrderBySuperPlusPrice`|

##### Method: `findByCarSharingOrderByCarSharing`
| JUnit test case |
|-|
| `GasStationRepositoryTest.findByCarSharingOrderByCarSharing`|

#### Class: `UserRepository`
##### Method: `findByPasswordAndEmail`
| JUnit test case |
|-|
| `UserRepositoryTest.testFindByPasswordAndEmail`|

##### Method: `findByEmail`
| JUnit test case |
|-|
| `UserRepositoryTest.testFindByEmail`|

### Package: `it.polito.ezgas.converter`
#### Class: `UserConverter`
##### Method: `toUserDto`
| JUnit test case |
|-|
| `UserConverterTest.testUserConverterToUserDto`|

##### Method: `toUser`
| JUnit test case |
|-|
| `UserConverterTest.testUserConverterToUser`|

## Step 2
### Package: `it.polito.ezgas.converter`
#### Class: `GasStationConverter`
##### Method: `toGasStationDto`
| JUnit test case |
|-|
| `GasStationConverterTest.testGasStationConverterToGasStationDto`|

##### Method: `toGasStation`
| JUnit test case |
|-|
| `GasStationConverterTest.testGasStationConverterToGasStation`|

#### Class: `UserServiceimpl`
##### Method: `getUserById`
 JUnit test case |
|-|
| `UserServiceImplTest.testGetUserByIdRightValue` |
| `UserServiceImplTest.testGetUserByIdNullValue` |
| `UserServiceImplTest.testGetUserByIdNegativeValue` |
| `UserServiceImplTest.testGetUserByIdNoUserFound` |

##### Method: `saveUser`
| JUnit test case |
|-|
| `UserServiceImplTest.testSaveUserOverlapping` |
| `UserServiceImplTest.testSaveUser` |

##### Method: `getAllUsers`
| JUnit test case |
|-|
| `UserServiceImplTest.testGetUsersZero` |
| `UserServiceImplTest.testGetUsersOne` |
| `UserServiceImplTest.testGetUsersTwo` |

##### Method: `deleteUser`
| JUnit test case |
|-|
| `UserServiceImplTest.testDeleteUserByIdNullValue` |
| `UserServiceImplTest.testDeleteUserByIdNegativeValue` |
| `UserServiceImplTest.testDeleteUserNotFound` |
| `UserServiceImplTest.testDeleteUser` |

##### Method: `login`
| JUnit test case |
|-|
| `UserServiceImplTest.testLoginNullCredentials` |
| `UserServiceImplTest.testLoginNotFound` |
| `UserServiceImplTest.testLogin` |

##### Method: `increaseUserReputation`
| JUnit test case |
|-|
| `UserServiceImplTest.testIncreaseUserReputationByIdNullValue` |
| `UserServiceImplTest.testIncreaseUserReputationByIdNegativeValue` |
| `UserServiceImplTest.testIncreaseUserReputationUserNotFound` |
| `UserServiceImplTest.testIncreaseUserReputationMaxValue` |
| `UserServiceImplTest.testIncreaseUserReputation` |

##### Method: `decreaseUserReputation`
| JUnit test case |
|-|
| `UserServiceImplTest.testDecreaseUserReputationByIdNullValue` |
| `UserServiceImplTest.testDecreaseUserReputationByIdNegativeValue` |
| `UserServiceImplTest.testDecreaseUserReputationUserNotFound` |
| `UserServiceImplTest.testDecreaseUserReputationMaxValue` |
| `UserServiceImplTest.testDecreaseUserReputation` |

## Step 3
### Package: `it.polito.ezgas.service.impl`
#### Class: `GasStationServiceimpl`
##### Method: `getGasStationById`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationByIdNullValue` |
| `GasStationServiceImplTest.testGetGasStationByIdNegativeValue` |
| `GasStationServiceImplTest.testGetGasStationByIdNotFound` |
| `GasStationServiceImplTest.testGetGasStationById` |

##### Method: `saveGasStation`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testSaveGasStationWrongCoordinates` |
| `GasStationServiceImplTest.testSaveGasStationRightCoordinates` |
| `GasStationServiceImplTest.testSaveGasStationInvalidPrice` |

##### Method: `getAllGasStations`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationZero` |
| `GasStationServiceImplTest.testGetGasStationOne` |
| `GasStationServiceImplTest.testGetGasStationTwo` |

##### Method: `deleteGasStation`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testDeleteGasStationNullValue` |
| `GasStationServiceImplTest.testDeleteGasStationNegativeValue` |
| `GasStationServiceImplTest.testDeleteGasStationNotFound` |
| `GasStationServiceImplTest.testDeleteGasStation` |

##### Method: `getGasStationsByGasolineType`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationByGasolineType` |
| `GasStationServiceImplTest.testGetGasStationByGasolineTypeWrongType` |
| `GasStationServiceImplTest.testGetGasStationByGasolineTypeNullType` |

##### Method: `getGasStationsByProximity(double lat, double  lon)`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetByProximityWrongCoordinates` |
| `GasStationServiceImplTest.testGetByProximityRightCoordinatesNotNullList` |

##### Method: `getGasStationsByProximity(double lat, double  lon, int radius)`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetByProximityWithRadiusWrongCoordinates` |
| `GasStationServiceImplTest.testGetByProximityWithRadiusRightCoordinatesNotNullList` |

##### Method: `getGasStationsWithCoordinates`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationsWithCoordinatesWrong` |
| `GasStationServiceImplTest.testGetGasStationsWithCoordinates` |

##### Method: `getGasStationsWithoutCoordinates`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationsWithoutCoordinates` |
| `GasStationServiceImplTest.testGetGasStationsWithoutCoordinatesWrongCarsharing` |
| `GasStationServiceImplTest.testGetGasStationsWithoutCoordinatesNullCarsharing` |
| `GasStationServiceImplTest.testGetGasStationsWithoutCoordinatesNullGasolineType` |


##### Method: `setReport`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testSetReportNegativeGasStationId` |
| `GasStationServiceImplTest.testSetReportNullGasStationId` |
| `GasStationServiceImplTest.testSetReportGasStationNotFound` |
| `GasStationServiceImplTest.testSetReportNullUserId` |
| `GasStationServiceImplTest.testSetReportUserNotFound` |
| `GasStationServiceImplTest.testSetReportPriceError` |
| `GasStationServiceImplTest.testSetReport` |
| `GasStationServiceImplTest.testSetReportCR4` |

##### Method: `getGasStationsByCarSharing`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testGetGasStationByCarSharing` |

##### Method: `isPriceValid`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testIsPriceValidWrongValues` |
| `GasStationServiceImplTest.testIsPriceValidRightValues` |

#### Method: `isCoordinateValid`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testIsCoordinateValidWrongValues` |
| `GasStationServiceImplTest.testIsCoordinateValidRightValues` |

##### Method: `dependability`
| JUnit test case |
|-|
| `GasStationServiceImplTest.dependabilityTimeStamp2DaysBefore` |
| `GasStationServiceImplTest.dependabilityNullTimeStamp` |
| `GasStationServiceImplTest.dependabilityAfterOneWeek` |
| `GasStationServiceImplTest.dependabilityUserNotFound` |

##### Method: `distance`
| JUnit test case |
|-|
| `GasStationServiceImplTest.testDistanceMethod` |


## Code coverage report

The snapshot regarding the repository coverage is not reported because Eclemma does not provide information about it.

<img src="/docs/TestCoverage/UserConverterCoverage.JPG" alt="UserConverterCoverage"/>

<img src="/docs/TestCoverage/GasStationConverterCoverage.JPG" alt="GasStationConverterCoverage"/>

<img src="/docs/TestCoverage/GasStationServiceImplCoverage.JPG" alt="GasStationServiceImplCoverage"/>

<img src="/docs/TestCoverage/UserServiceImplCoverage.JPG" alt="UserServiceImplCoverage"/>


# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UCx.y


| Scenario |  name |
| ------------- |:-------------:|
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |  
|  2     |  ... |


# Coverage of Scenarios and FR

<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR.
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >

The Scenario IDs shown in the table refer to the nominal scenarios relating to the UCs.

| Scenario ID | Functional Requirements covered | JUnit  Test(s) |
| ----------- | ------------------------------- | ----------- |
| 1	| FR1.1	| `UserServiceImplTest.testSaveUserOverlapping` <br> `UserServiceImplTest.testSaveUser` |
| 2	| FR1.1	| `UserServiceImplTest.testSaveUserOverlapping` <br> `UserServiceImplTest.testSaveUser` |
| 3	| FR1.2	| `UserServiceImplTest.testDeleteUserByIdNullValue` <br> `UserServiceImplTest.testDeleteUserByIdNegativeValue` <br> `UserServiceImplTest.testDeleteUserNotFound` <br> `UserServiceImplTest.testDeleteUser` |
| 4	| FR3.1	| `GasStationServiceImplTest.testSaveGasStationWrongCoordinates` <br> `GasStationServiceImplTest.testSaveGasStationRightCoordinates` <br> `GasStationServiceImplTest.testSaveGasStationInvalidPrice` |
| 5	| FR3.1	| `GasStationServiceImplTest.testSaveGasStationWrongCoordinates` <br> `GasStationServiceImplTest.testSaveGasStationRightCoordinates` <br> `GasStationServiceImplTest.testSaveGasStationInvalidPrice` |
| 6	| FR3.2	| `GasStationServiceImplTest.testDeleteGasStationNullValue` <br> `GasStationServiceImplTest.testDeleteGasStationNegativeValue` <br> `GasStationServiceImplTest.testDeleteGasStationNotFound` <br> `GasStationServiceImplTest.testDeleteGasStation` |
| 7	| FR5.1	| `GasStationServiceImplTest.testSetReportNegativeGasStationId` <br> `GasStationServiceImplTest.testSetReportNullGasStationId` <br> `GasStationServiceImplTest.testSetReportGasStationNotFound` <br> `GasStationServiceImplTest.testSetReportNullUserId` <br> `GasStationServiceImplTest.testSetReportUserNotFound` <br> `GasStationServiceImplTest.testSetReportPriceError` <br> `GasStationServiceImplTest.testSetReport` <br> `GasStationServiceImplTest.testSetReportCR4` |
| 8	| FR4.1, FR4.3, FR4.4, FR4.5 | `GasStationServiceImplTest.testGetByProximityWrongCoordinates` <br> `GasStationServiceImplTest.testGetByProximityRightCoordinatesNotNullList` <br> `GasStationServiceImplTest.testGetByProximityWithRadiusWrongCoordinates` <br> `GasStationServiceImplTest.testGetByProximityWithRadiusRightCoordinatesNotNullList` <br> `GasStationServiceImplTest.testGetGasStationsWithCoordinatesWrong` <br> `GasStationServiceImplTest.testGetGasStationsWithCoordinates` <br>  `GasStationServiceImplTest.testGetGasStationsWithoutCoordinates` <br> `GasStationServiceImplTesttestGetGasStationsWithoutCoordinatesWrongCarsharing` <br> `GasStationServiceImplTesttestGetGasStationsWithoutCoordinatesNullCarsharing` <br> `GasStationServiceImplTesttestGetGasStationsWithoutCoordinatesNullGasolineType` |
| 9	| FR5.2 | `GasStationServiceImplTest.dependabilityTimeStamp2DaysBefore` <br> `GasStationServiceImplTest.dependabilityNullTimeStamp` <br> `GasStationServiceImplTest.dependabilityAfterOneWeek` <br> `GasStationServiceImplTest.dependabilityUserNotFound` |
| 10.1	| FR5.2, FR5.3 | `UserServiceImplTest.testIncreaseUserReputationByIdNullValue` <br> `UserServiceImplTest.testIncreaseUserReputationByIdNegativeValue` <br> `UserServiceImplTest.testIncreaseUserReputationUserNotFound` <br> `UserServiceImplTest.testIncreaseUserReputationMaxValue` <br> `UserServiceImplTest.testIncreaseUserReputation` |             
| 10.2	| FR5.2, FR5.3 | `UserServiceImplTest.testDecreaseUserReputationByIdNullValue` <br> `UserServiceImplTest.testDecreaseUserReputationByIdNegativeValue` <br> `UserServiceImplTest.testDecreaseUserReputationUserNotFound` <br> `UserServiceImplTest.testDecreaseUserReputationMaxValue` <br> `UserServiceImplTest.testDecreaseUserReputation` |             

# Coverage of Non Functional Requirements

###

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|      NFR2                      |    Each test is completed in less than 0.5 seconds   |
