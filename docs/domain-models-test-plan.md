# Domain Models, Class Diagrams and Test Plan

## Notes

The full workings for the domain models can be found in this [miro board](https://miro.com/welcomeonboard/amJJOXpPNXZ6RFM0bGh2dEd3OE5vbUd4aGwxNVdwVVIxd3Y0ZTg0ZHlablZEamExaDlaeTFTenp2VWtZTW9ZVnwzNDU4NzY0NTg1NjgzNTc1ODgzfDI=?share_link_id=128081926157).

Domain models and tests here are shown per class. Only public methods have been included. The relationship
between types and their members can be found in the [class diagram](./class-diagram.md). 

## AddressBook

| property                           | message                                                | output                    |
|------------------------------------|--------------------------------------------------------|---------------------------|
| contacts @list<@IImmutableContact> | addContact(@IImmutableContact)                         | @boolean                  |
| emailAddresses @Set<@String>       | deleteAllContacts()                                    | @void                     |
| phoneNumbers @Set<@String>         | deleteContact(@IImmutableContact)                      | @IImmutableContact        |
|                                    | getContacts()                                          | @List<@IImmutableContact> |
|                                    | replaceContact(@IImmutableContact, @IImmutableContact) | @IImmutableContact        |
|                                    | searchContacts(@String)                                | List<@IImmutableContact>  |

### AddressBook Tests

#### Add a contact
- [X] AB1:	should increment the size of contacts by 1 when new contract is added
- [X] AB2:	should include new contact in contacts when a new contract is added
- [X] AB3:	Should return true when new contact is added
- [X] AB4:	should throw exception when a contact with a value of null is added

#### Search contacts
- [X] AB5:	should return list with a size of 1 where a match to only one contact is found
- [X] AB6:	should return list containing matched contact where a match to only one contact is found
- [X] AB7:	should return an empty list where no matches found
- [X] AB8:	should return list with a size of 2 where a match to two contacts is found
- [X] AB9:	should return list containing both matched contacts where a match to two contacts is found
- [X] AB10:	should perform case-insensitive search
- [X] AB11:	should trim the search term before searching

#### Delete contact
- [X] AB12:	should decrement contacts size by 1 where a contact in contacts is passed
- [X] AB13:	should remove contact from contracts where a contact in contacts is passed
- [X] AB14:	should return contact removed where a contact in contacts is passed
- [X] AB15:	should return null where a contact not in contacts is passed

#### Duplicate Protection
- [X] AB16:	Should not add a contact where another contact has the same phone number
- [X] AB17:	Should not add a contact where another contact has the same email address
- [X] AB18:	Should add a contact where another contact, removed from contacts, has the same phone number
- [X] AB19:	Should add a contact where another contact, removed from contacts, has the same email address

#### Update Contact
- [X] AB20:	Should replace contact with new contact
- [X] AB21:	Should return updated contact when contact updated
- [X] AB22:	Should throw exception where another contact has the same phoneNumber
- [X] AB23:	Should throw exception where another contact has the same emailAddress
- [X] AB24:	Should add a contact where another contact, added with the same phone number, is updated with a new number
- [X] AB25:	Should add a contact where another contact, added with the same email address, is updated with a new email address
- [X] AB26:	Should return null if contact to replace not found
- [X] AB27:	Should include contact in contacts if error thrown

#### View All Contacts
- [X] AB28:	Should return collection with one element where one contact
- [X] AB29:	Should return collection with all contacts where more than one contact
- [X] AB30:	Should return empty collection where no contacts
- [X] AB31:	Adding an element to the returned collection should not add an element to the contacts in Address Book

#### Search by Phone Number
- [X] AB32:	should return list with a size of 1 where phone number is matched to just one contact
- [X] AB33:	should return list containing matched contact where phone number is matched to just one contact
- [X] AB34:	should return an empty list where no phone number matches found
- [X] AB35:	should return list with a size of 2 where partial phone number is matched to two contacts
- [X] AB36:	should return list containing both matched contacts where partial phone number is matched to two contacts

#### Search by Email Address
- [X] AB37:	should return list with a size of 1 where email address is matched to just one contact
- [X] AB38:	should return list containing matched contact where email address is matched to just one contact
- [X] AB39:	should return an empty list where no email address matches found
- [X] AB40:	should return list with a size of 2 where partial email address is matched to two contacts
- [X] AB41:	should return list containing both matched contacts where partial email address is matched to two contacts

#### Sorting Results
- [X] AB42:	Results of search should be in alphabetical order

#### Delete All Contacts
- [X] AB43:	Contacts should be an empty list after deletion
- [X] AB44:	Should add contact where phone number is the same as a contact removed by delete all
- [X] AB45:	Should add contact where email address is the same as a contact removed by delete all

## ImmutableContract

| property             | message                                    | output             |
|----------------------|--------------------------------------------|--------------------|
| emailAddress @String | ImmutableContact(@String, @String,@String) | @IImmutableContact |
| name @String         | getName()                                  | @String            |
| phoneNumber @ String | getPhoneNumber()                           | @String            |
|                      | getEmailAddress()                          | @String            |
|                      | withName(@String)                          | @String            |
|                      | withPhoneNumber(@String)                   | @IImmutableContact |
|                      | withEmailAddress(@String)                  | @IImmutableContact |

### ImmutableContract Tests

#### Constructor - Name
- [X] IC1:	should return correct name
- [X] IC2:	should trim contact name
- [X] IC3:	should throw exception where null passed for name
- [X] IC4:	should throw exception where an empty string is passed for name

#### Constructor - Phone number
- [X] IC5:	should return correct phone number
- [X] IC6:	should throw error is phone number is null
- [X] IC7:	should throw error is phone number is empty
- [X] IC8:	should trim phone number

#### Constructor - Email Address
- [X] IC9:	should return correct email address
- [X] IC10:	should throw error is email address is null
- [X] IC11:	should throw error is email address is empty
- [X] IC12:	should trim email address

#### Constructor - Pattern Validation
- [X] IC13:	should ensure that phone number follows valid pattern
- [X] IC14:	should ensure that email address follows valid pattern

#### With Name
- [X] IC15:	Should return Contact with updated name when with name is called
- [X] IC16:	Should return Contact with only the name updated when with name is called
- [X] IC17:	Should not mutate the object on which with name is called

#### With Phone Number
- [X] IC18:	Should return Contact with updated phone number when with phone number is called
- [X] IC19:	Should return Contact with only the phone number updated when with phone number is called
- [X] IC20:	Should not mutate the object on which with phone number is called

#### With Email Address
- [X] IC21:	Should return Contact with updated email address when with email address is called
- [X] IC22:	Should return Contact with only the email address updated when with email address is called
- [X] IC23:	Should not mutate the object on which with email address is called

## AddressBookApp

| properties                                  | messages | output |
|---------------------------------------------|----------|--------|
| addressBook @IAddressBook                   | run()    | @void  |
| addressBookMenu @SortedMap<@String,@String> |          |        |
| contactMenu @SortedMap<@String, @String>    |          |        |
| userInterface @IUserInterface               |          |        |

### AddressBookApp Tests

#### Run - Create Contact
- [X] APP1:   	Should call add contact, passing a contact object with the correct state
- [X] APP2:	    Should handle error when invalid input for new contact

#### Run - Read Contacts
- [X] APP3:	    Should call printMenu with a 1-based index for keys mapped to usernames as values
- [X] APP4:	    Should print error message where no contact's found

#### Run - Read Contact
- [X] APP5:	    Should call printContact with the selected contact

#### Run - Update Contact
- [X] APP6:     Should update contact in contacts with correct values
- [X] APP7:	    Should handle error when invalid input for contact updates

#### Run - Delete Contact
- [X] APP8:	Should remove contact from contacts

#### Run - Search
- [X] APP9:	    Should call printMessage with each matching contact's name, prefixed with a 1-based index number and separated by newlines where multiple matches
- [X] APP10:	Should call print contact with matching contact where one match
- [X] APP11:	Should print warning message that no matching contacts found where no matches
- [X] APP12:    Should print error message where search term has no content

#### Run - Delete All Contacts
- [X] APP13:	Should print warning message asking to confirm delete all contacts
- [X] APP14:	Should call delete all contacts if user enters "YES"
- [X] APP15:	Should not call delete all contacts if user enters "NO"
- [ ] APP16:	Should re-prompt user for confirmation where invalid input received "YES" or "NO"
- [ ] APP17:	Should print error where there are no contacts to delete

## ConsoleInterface

| property | message                                | output  |
|----------|----------------------------------------|---------|
|          | getUserInput(@String)                  | @String |
|          | printErrorMessage(@String)             | @void   |
|          | printMessage(@String)                  | @void   |
|          | printSuccessMessage(@String)           | @void   |
|          | printWarningMessage(@String)           | @void   |
|          | printContact(@IImmutableContact)       | @void   |
|          | printMenu(@SortedMap<@String,@String>) | @void   |

## ConsoleInterface Tests

- [X] CI1:	Should print passed message to console
- [X] CI2:	Should print passed prompt to console
- [X] CI3:	Should return correct userInput
- [X] CI4:	Should print passed error message to console with red text
- [X] CI5:	Should print passed warning message to console with yellow text
- [X] CI6:	Should print passed success message to console with green text
- [X] CI7:  Should print contact's name, phone number and email address when contact passed
- [X] CI8:  Should throw error where contact is null
- [X] CI9:  Should print each key and value of passed menu
- [X] CI10: Should throw error where menu is null
- [X] CI11: Should throw error where menu is empty

## StringValidation

| property | message                      | output  |
|----------|------------------------------|---------|
|          | hasContent(@String)          | @bool   |
|          | isValidPhoneNumber(@String)  | @bool   |
|          | isValidEmailAddress(@String) | @bool   |


## StringValidation Tests

- [X] SV1:	hasContent should return true if argument is not empty or null
- [X] SV2:	hasContent should return false if argument is null
- [X] SV3:	hasContent should return false if trimmed argument is empty
- [X] SV4:  isValidPhoneNumber should return true if argument is valid phone number
- [X] SV5:	isValidPhoneNumber should return false if argument is null
- [X] SV6:	isValidPhoneNumber should return false if argument is empty
- [X] SV7:	isValidPhoneNumber should return false if chars after the first char are not digits
- [X] SV8:	isValidPhoneNumber should return false if first char of argument is not a '+' or digit
- [X] SV9:	isValidEmailAddress should return true if argument is valid email
- [X] SV10: isValidEmailAddress should return false if argument is null
- [X] SV11: isValidEmailAddress should return false if argument is empty
- [X] SV12: isValidEmailAddress should return false if argument does not contain '@'
- [X] SV13: isValidEmailAddress should return false if argument does not contain '.' after '@'







