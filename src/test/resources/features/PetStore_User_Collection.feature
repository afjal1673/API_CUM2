Feature: PetStore_User_Collection

Scenario: PetStore_User_Collection
	Given I generate all test random value
	When I Create a User
	Then I GetUser
	Then UpdateUser
	Then CheckUpdate
	And DeleteUser
	Then TestDelete