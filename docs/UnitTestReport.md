# Unit Testing Documentation

Authors: Andrea Zappavigna, Dong Liu, Michele Filippini, Giovanni Brignone

Date: 14/05/2020

| Version | Contents |
| -------- | --------- |
| 1 | Unit tests on entities User, GasStation |
| 2 | Unit tests on entities UserDto, GasStationDto, LoginDto, IdPw |

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)

- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

All the tests described through the black box method refer to methods in which the parameters supplied at the input are checked. Many of these tests, for which a not valid result is expected, were not carried out in JUnit since the methods provided in the package entity for the GasStation and User classes do not carry out any control and therefore would lead to an insignificant result being that as long as they respect the defined type, all values are accepted and stored.


### **Class entity/User.java - constructor method**

 **Criteria for method *User*:**
  - userName, password and email must be strings
  - reputation must be a integer ranging between -5 and 5


 **Predicates for method *User*:**

 | Criteria | Predicate |
 | -------- | --------- |
 | Correctness | userName instanceof String == True |
 | | password instanceof String == True |
 | | email instanceof String == True |
 | | reputation instanceof Integer == True |


 **Boundaries**:

 | Criteria | Boundary values |
 | -------- | --------------- |
 | Correctness | "" (empty string) |

Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null. Any given set of values passes the correctness check if all values are individually correct (logic AND of predicates).

 **Combination of predicates**:

 | Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
 |-------|-------|-------|-------|
 |true|V|User("Mario", "Rossi", "mariorossi@ezgas.com", 0)| void it.polito.ezgas.EZGasApplicationTests.testUserConstructor() |
 |false|I|User("Mario", "", "mariorossi@ezgas.com", 3)||

### **Class entity/User.java - setReputation method**

Getters and setters are extremely simple pieces of code, they do not contain any logic and are stateless; thus only one of them will be considered foe each class as an example.

 **Predicates for method *setReputation*:**

 | Criteria | Predicate |
 | -------- | --------- |
 | Correctness | reputation instanceof Integer == True  |
 | Range | reputation >= -5 && reputation <= 5 |


 **Boundaries**:

 | Criteria | Boundary values |
 | -------- | --------------- |
 | Correctness | "" (empty string) |
 | Range | >= -5 && <= 5 |
 | | < -5 |
 | | > 5 |

Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

 **Combination of predicates**:

 | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
 |-------|-------|-------|-------|-------|
 |true|< -5|I|setReputation(-7)||
 |true|> 5|I|setReputation(8)||
 |true|>= -5 && <= 5|V|setReputation(0)| void it.polito.ezgas.EZGasApplicationTests.testUserGetterAndSetter() |
 |false|< -5|I|setReputation(true)||
 |false|> 5|I|setReputation("3")||
 |false|>= -5 && <= 5|I|setReputation(5.0)||

 ### **Class entity/GasStation.java - constructor method**

  **Criteria for method *GasStation*:**
   - gasStationName, gasStationAddress, carSharing and reportTimestamp must be strings
   - hasDiesel, hasSuper, hasSuperPlus, hasGas and hasMethane must be booleans
   - dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, lat, lon and reportDependability must be doubles
   - reportUser must be a integer


  **Predicates for method *GasStation*:**

  | Criteria | Predicate |
  | -------- | --------- |
  | Correctness | gasStationName instanceof String == True |
  | | hasDiesel instanceof Boolean == True |
  | | dieselPrice instanceof Double == True |
  | | reportUser instanceof Integer == True |
  | | etc.. |


  **Boundaries**:

  | Criteria | Boundary values |
  | -------- | --------------- |
  | Correctness | "" (empty string) |


 Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.  Any given set of values passes the correctness check if all values are individually correct (logic AND of predicates).

  **Combination of predicates**:

  | Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
  |-------|-------|-------|-------|
  |true|V|GasStation("Eni Abruzzi","Corso Duca degli Abruzzi 42", true, true, false, false, false, "Eni Enjoy", 45.08, 07.41, 1.58, 1.63, 0, 0, 0, 0, "00:00:00", 10)| void it.polito.ezgas.EZGasApplicationTests.testGasStationConstructor() |
  |false|I|GasStation("")| |

  ### **Class entity/GasStation.java - setLat method**

   **Criteria for method *setLat*:**
  - lat must be a double
  - lat value ranging between -90 and 90


   **Predicates for method *setLat*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | lat instanceof Double == True |
   | Range | lat >= -90.0 && lat <= 90.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= -90.0 && <= 90.0 |
   | | < -90.0 |
   | | > 90.0 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< -90.0|I|setLat(true)| |
   |false|> 90.0|I|setLat("80")| |
   |false|>= -90.0 && <= 90.0|I|setLat(null)| |
   |true|< -90.0|I|setLat(-92.42)| |
   |true|> 90.0|I|setLat(108.12)| ||
   |true|>= -90.0 && <= 90.0|V|setLat(45.33)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |

 ### **Class entity/GasStation.java - setLon method**

   **Criteria for method *setLon*:**
  - lon must be a double
  - lon value ranging between -180 and 180


   **Predicates for method *setLon*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | lon instanceof Double == True |
   | Range | lon >= -180.0 && lon <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= -180.0 && <= 180.0 |
   | | < -180.0 |
   | | > 180.0 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< -180.0|I|setLon(true)| |
   |false|> 180.0|I|setLon("80")| |
   |false|>= -180.0 && <= 180.0|I|setLon(null)| |
   |true|< -180|I|setLon(-182.42)| |
   |true|> 180|I|setLon(188.12)| ||
   |true|>= -180.0 && <= 180.0|V|setLon(45.33)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |

  ### **Class entity/GasStation.java - setReportDependability method**

   **Criteria for method *setReportDependability*:**
  - reportDependability must be a double
  - reportDependability value ranging between 0 and 100


   **Predicates for method *setReportDependability*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | reportDependability instanceof Double == True |
   | Range | reportDependability >= -180.0 && reportDependability <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= 0 && <= 100 |
   | | < 0 |
   | | > 100 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< 0|I|setReportDependability(true)| |
   |false|> 100|I|setReportDependability("80")| |
   |false|>= 0 && <= 100|I|setReportDependability(null)| |
   |true|< 0|I|setReportDependability(-1)| |
   |true|> 100|I|setReportDependability(102)| ||
   |true|>= 0 && <= 100|V|setReportDependability(100)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |


   ### **Class service/GasStationServiceImpl.java - distance method**

   **Criteria for method *setReportDependability*:**
  - reportDependability must be a double
  - reportDependability value ranging between 0 and 100


   **Predicates for method *setLon*:**

   | Criteria | Predicate |
   | -------- | --------- |
   | Correctness | reportDependability instanceof Double == True |
   | Range | reportDependability >= -180.0 && reportDependability <= 180.0 |


   **Boundaries**:

   | Criteria | Boundary values |
   | -------- | --------------- |
   | Correctness | "" (empty string) |
   | Range | >= 0 && <= 100 |
   | | < 0 |
   | | > 100 |

  Note: The null value was not considered as boundary case because the expression `x instanceof Class` is always false if `x` is null.

   **Combination of predicates**:

   | Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
   |-------|-------|-------|-------|-------|
   |false|< 0|I|setReportDependability(true)| |
   |false|> 100|I|setReportDependability("80")| |
   |false|>= 0 && <= 100|I|setReportDependability(null)| |
   |true|< 0|I|setReportDependability(-1)| |
   |true|> 100|I|setReportDependability(102)| ||
   |true|>= 0 && <= 100|V|setReportDependability(100)| void it.polito.ezgas.EZGasApplicationTests.testGasStationGetterAndSetter() |


# White Box Unit Tests

### Test cases definition

To make the document more readable, individual units are grouped together.

#### Unit name - it.polito.ezgas.entity.User

| methods |
|-|
|`void setAdmin(Boolean admin)`|
|`void setEmail(String email)`|
|`void setPassword(String password)`|
|`void setReputation(Integer reputation)`|
|`void setUserId(Integer userId)`|
|`void setUserName(String userName)`|
|`Boolean getAdmin()`|
|`String getEmail()`|
|`String getPassword()`|
|`Integer getReputation()`|
|`Integer getUserId()`|
|`String getUserName()`|
|`User(String userName, String password, String email, Integer reputation)`|

#### Unit name - it.polito.ezgas.entity.GasStation

|methods|
|-|
|`void setCarSharing(String carSharing)`|
|`String getCarSharing()`|
|`void setDieselPrice(Double dieselPrice)`|
|`void setHasDiesel(boolean hasDiesel)`|
|`Double getDieselPrice()`|
|`boolean getHasDiesel()`|
|`void setPremiumDieselPrice(Double premiumDieselPrice)`|
|`void setHasPremiumDiesel(boolean hasPremiumDiesel)`|
|`Double getPremiumDieselPrice()`|
|`boolean getHasPremiumDiesel()`|
|`void setSuperPrice(Double superPrice)`|
|`void setHasSuper(boolean hasSuper)`|
|`Double getSuperPrice()`|
|`boolean getHasSuper()`|
|`void setSuperPlusPrice(Double superPlusPrice)`|
|`void setHasSuperPlus(boolean hasSuperPlus)`|
|`Double getSuperPlusPrice()`|
|`boolean getHasSuperPlus()`|
|`void setMethanePrice(Double methanePrice)`|
|`void setHasMethane(boolean hasMethane)`|
|`Double getMethanePrice()`|
|`boolean getHasMethane()`|
|`void setGasPrice(Double gasPrice)`|
|`void setHasGas(boolean hasGas)`|
|`Double getGasPrice()`|
|`boolean getHasGas()`|
|`void setLat(double lat)`|
|`void setLon(double lon)`|
|`double getLat()`|
|`double getLon()`|
|`void setGasStationAddress(String gasStationAddress)`|
|`String getGasStationAddress()`|
|`void setGasStationId(Integer gasStationId)`|
|`Integer getGasStationId()`|
|`void setGasStationName(String gasStationName)`|
|`String getGasStationName()`|
|`void setReportDependability(double reportDependability)`|
|`double getReportDependability()`|
|`void setReportTimestamp(String reportTimestamp)`|
|`String getReportTimestamp()`|
|`void setReportUser(Integer reportUser)`|
|`Integer getReportUser()`|
|`void setUser(User user)`|
|`User getUser()`|
|`GasStation(String gasStationName, String gasStationAddress, boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane, boolean HasPremiumDiesel, String carSharing, double lat, double lon, Double dieselPrice, Double superPrice, Double superPlusPrice, Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer reportUser, String reportTimestamp, double reportDependability)`|



#### Unit name - it.polito.ezgas.dto.UserDto

| methods |
|-|
|`void setUserId(Integer userId)`|
|`void setUserName(String userName)`|
|`void setPassword(String password)`|
|`void setEmail(String email)`|
|`void setReputation(Integer reputation)`|
|`void setAdmin(Boolean admin)`|
|`Boolean getAdmin()`|
|`String getEmail()`|
|`String getPassword()`|
|`Integer getReputation()`|
|`Integer getUserId()`|
|`String getUserName()`|
|`UserDto(Integer userId, String userName, String password, String email, Integer reputation)`|
|`UserDto(Integer userId, String userName, String password, String email, Integer reputation, Boolean admin)`|


#### Unit name - it.polito.ezgas.dto.GasStationDto

|methods|
|-|
|`void setCarSharing(String carSharing)`|
|`String getCarSharing()`|
|`void setDieselPrice(Double dieselPrice)`|
|`void setHasDiesel(boolean hasDiesel)`|
|`Double getDieselPrice()`|
|`boolean getHasDiesel()`|
|`void setPremiumDieselPrice(Double premiumDieselPrice)`|
|`void setHasPremiumDiesel(boolean hasPremiumDiesel)`|
|`Double getPremiumDieselPrice()`|
|`boolean getHasPremiumDiesel()`|
|`void setSuperPrice(Double superPrice)`|
|`void setHasSuper(boolean hasSuper)`|
|`Double getSuperPrice()`|
|`boolean getHasSuper()`|
|`void setSuperPlusPrice(Double superPlusPrice)`|
|`void setHasSuperPlus(boolean hasSuperPlus)`|
|`Double getSuperPlusPrice()`|
|`boolean getHasSuperPlus()`|
|`void setMethanePrice(Double methanePrice)`|
|`void setHasMethane(boolean hasMethane)`|
|`Double getMethanePrice()`|
|`boolean getHasMethane()`|
|`void setGasPrice(Double gasPrice)`|
|`void setHasGas(boolean hasGas)`|
|`Double getGasPrice()`|
|`boolean getHasGas()`|
|`void setLat(double lat)`|
|`void setLon(double lon)`|
|`double getLat()`|
|`double getLon()`|
|`void setGasStationAddress(String gasStationAddress)`|
|`String getGasStationAddress()`|
|`void setGasStationId(Integer gasStationId)`|
|`Integer getGasStationId()`|
|`void setGasStationName(String gasStationName)`|
|`String getGasStationName()`|
|`void setReportDependability(double reportDependability)`|
|`double getReportDependability()`|
|`void setReportTimestamp(String reportTimestamp)`|
|`String getReportTimestamp()`|
|`void setReportUser(Integer reportUser)`|
|`Integer getReportUser()`|
|`void setUserDto(UserDto userDto)`|
|`UserDto getUserDto()`|
|`GasStationDto(String gasStationName, String gasStationAddress, boolean hasDiesel, boolean hasSuper, boolean hasSuperPlus, boolean hasGas, boolean hasMethane, boolean HasPremiumDiesel, String carSharing, double lat, double lon, Double dieselPrice, Double superPrice, Double superPlusPrice, Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer reportUser, String reportTimestamp, double reportDependability)`|

#### Unit name - it.polito.ezgas.dto.LoginDto

| methods |
|-|
|`void setUserId(Integer userId)`|
|`void setUserName(String userName)`|
|`void setPassword(String password)`|
|`void setEmail(String email)`|
|`void setReputation(Integer reputation)`|
|`void setAdmin(Boolean admin)`|
|`void setToken(String token)`
|`Boolean getAdmin()`|
|`String getEmail()`|
|`String getPassword()`|
|`Integer getReputation()`|
|`Integer getUserId()`|
|`String getUserName()`|
|`String getToken()`
|`LoginDto(Integer userId, String userName,String token, String email, Integer reputation)`|

#### Unit name - it.polito.ezgas.dto.IdPw

| methods |
|-|
|`String getUser()`|
|`void setUser(String user)`|
|`String getPw()`|
|`void setPw(String pw)`|
|`IdPw(String id, String pw)`|


#### Unit name - it.polito.ezgas.dto.PriceReportDto

|methods|
|-|
|`void setDieselPrice(Double dieselPrice)`|
|`Double getDieselPrice()`|
|`void setPremiumDieselPrice(Double premiumDieselPrice)`|
|`Double getPremiumDieselPrice()`|
|`void setSuperPrice(Double superPrice)`|
|`Double getSuperPrice()`|
|`void setSuperPlusPrice(Double superPlusPrice)`|
|`Double getSuperPlusPrice()`|
|`void setMethanePrice(Double methanePrice)`|
|`Double getMethanePrice()`|
|`void setGasPrice(Double gasPrice)`|
|`Double getGasPrice()`|
|`void setGasStationId(Integer gasStationId)`|
|`Integer getGasStationId()`|
|`void setUserId(Integer userId)`|
|`Integer getUserId()`|
|`PriceReportDto(Integer gasStationId, Double dieselPrice, Double superPrice, Double superPlusPrice, Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer userId)`|


#### Unit to tests mapping recap
| Unit name | JUnit test case |
|--|--|
| it.polito.ezgas.entity.User | EZGasApplicationTests.testUserGetterAndSetter |
| | EZGasApplicationTests.testUserConstructor |
| it.polito.ezgas.entity.GasStation | EZGasApplicationTests.testGasStationGetterAndSetter |
| | EZGasApplicationTests.testGasStationConstructor |
| it.polito.ezgas.dto.UserDto | UserDtoTest.testUserDtoGetterAndSetter |
| | UserDtoTest.testUserDtoConstructor |
| | UserDtoTest.testUserDtoConstructorWithAdmin |
| it.polito.ezgas.dto.GasStationDto | GasStationDtoTest.testGasStationDtoGetterAndSetter |
| | GasStationDtoTest.testGasStationConstructor |
| it.polito.ezgas.dto.LoginDto | LoginDtoTest.testLoginDtoGetterSetter |
| | LoginDtoTest.testLoginDtoConstructor |
| it.polito.ezgas.dto.IdPw | IdPwTest.testIdPwGetterSetter |
| | IdPwTest.testIdPwConstructor |
| it.polito.ezgas.dto.PriceReportDto | PriceReportDtoTest.testPriceReportDtoGetterSetter |
| | PriceReportDtoTest.testPriceReportDtoConstructor |

### Code coverage report

<img src="/docs/TestCoverage/GasStationAndUserCoverage.JPG" alt="GasStationAndUserCoverage"/>

<img src="/docs/TestCoverage/gasStationDtoCoverage.JPG" alt="GasStationDtoCoverage"/>

The coverage of GasStationDto is different from 100% since the class GasStationDto contains duplicated methods.

<img src="/docs/TestCoverage/idPwcoverage.JPG" alt="idPwCoverage"/>

<img src="/docs/TestCoverage/loginDtoCoverage.JPG" alt="LoginDtoCoverage"/>

<img src="/docs/TestCoverage/userDtoCoverage.JPG" alt="UserDtoCoverage"/>

<img src="/docs/TestCoverage/priceReportDtoCoverage.JPG" alt="PriceReportDtoCoverage"/>

### Loop coverage analysis

The loop coverage analysis was not carried out as none of the units tested have loops inside.

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||
