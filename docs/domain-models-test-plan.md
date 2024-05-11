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
- [ ] AB5:	should return list with a size of 1 where a match to only one contact is found
- [ ] AB6:	should return list containing matched contact where a match to only one contact is found
- [ ] AB7:	should return an empty list where no matches found
- [ ] AB8:	should return list with a size of 2 where a match to two contacts is found
- [ ] AB9:	should return list containing both matched contacts where a match to two contacts is found
- [ ] AB10:	Should perform case-insensitive search
- [ ] AB11:	should trim the search term before searching

#### Delete contact
- [ ] AB12:	should decrement contacts size by 1 where a contact in contacts is passed
- [ ] AB13:	should remove contact from contracts where a contact in contacts is passed
- [ ] AB14:	should return contact removed where a contact in contacts is passed
- [ ] AB15:	should return null where a contact not in contacts is passed

#### Duplicate Protection
- [ ] AB16:	Should not add a contact where another contact has the same phone number
- [ ] AB17:	Should not add a contact where another contact has the same email address
- [ ] AB18:	Should add a contact where another contact, removed from contacts, has the same phone number
- [ ] AB19:	Should add a contact where another contact, removed from contacts, has the same email address

#### Update Contact
- [ ] AB20:	Should replace contact with new contact
- [ ] AB21:	Should return updated contact when contact updated
- [ ] AB22:	Should not update a contact where another contact has the same phoneNumber
- [ ] AB23:	Should not update a contact where another contact has the same emailAddress
- [ ] AB24:	Should return old contact when contact not updated
- [ ] AB25:	Should add a contact where another contact, added with the same phone number, is updated with a new number
- [ ] AB26:	Should add a contact where another contact, added with the same email address, is updated with a new email address
- [ ] AB27:	Should return null if contact to replace not found

#### View All Contacts
- [ ] AB28:	Should return collection with one element where one contact
- [ ] AB29:	Should return collection with all contacts where more than one contact
- [ ] AB30:	Should return empty collection where no contacts
- [ ] AB31:	Adding an element to the returned collection should not add an element to the contacts in Address Book

#### Sorting Results
- [ ] AB32:	Results of search should be in alphabetical order

#### Delete All Contacts
- [ ] AB33:	Contacts should be an empty list after deletion
- [ ] AB34:	Should add contact where email address is the same as a contact removed by delete all
- [ ] AB35:	Should add contact where phone number is the same as a contact removed by delete all

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
- [ ] IC10:	should throw error is email address is null
- [ ] IC11:	should throw error is email address is empty
- [ ] IC12:	should trim email address

#### Constructor - Pattern Validation
- [ ] IC13:	should ensure that phone number follows pattern \/^[+\d]\d*$/gm\
- [ ] IC14:	should ensure that email address follows pattern \.*@.*\.*\

#### With Name
- [ ] IC15:	Should return Contact with updated name when set name is called
- [ ] IC16:	Should return Contact with only the name updated when set name is called
- [ ] IC17:	Should not mutate the object on which set name is called

#### With Phone Number
- [ ] IC18:	Should return Contact with updated phone number when set phone number is called
- [ ] IC19:	Should return Contact with only the phone number updated when set phone number is called
- [ ] IC20:	Should not mutate the object on which set phone number is called

#### With Email Address
- [ ] IC21:	Should return Contact with updated email address when set email address is called
- [ ] IC22:	Should return Contact with only the email address updated when set email address is called
- [ ] IC23:	Should not mutate the object on which set email address is called

## AddressBookApp

| properties                                  | messages | output |
|---------------------------------------------|----------|--------|
| addressBook @IAddressBook                   | run()    | @void  |
| addressBookMenu @SortedMap<@String,@String> |          |        |
| contactMenu @SortedMap<@String, @String>    |          |        |
| userInterface @IUserInterface               |          |        |

### AddressBookApp Tests

#### Run - Top-Menu

- [ ] ABA1:	Should call printMessage with top-level menu options preceded by 1-based index and separated by newlines
- [ ] ABA2:	Should call getUserInput with a prompt to select a contact by index number or press e for exit
- [ ] ABA3:	Should print error message where invalid input received
- [ ] ABA4:	Should call getUserInput again where invalid input received

#### Run - Create Contact
- [ ] ABA5:   	Should call getUserInput with a prompt for a contact's name
- [ ] ABA6:	    Should call getUserInput with a prompt for a contact's phone number
- [ ] ABA7:	    Should call getUserInput with a prompt for a contact's email
- [ ] ABA8:	    Should call printErrorMessage with an error message where an error is thrown
- [ ] ABA9:	    Should call add contact, passing a contact object with the correct state
- [ ] ABA10:	Should print success message if contact added successfully

#### Run - Read Contacts
- [ ] ABA11:	Should call printMessage with each contact's name, prefixed with a 1-based index number, e.g. "1. Jane Doe", "2. John Doe" and separated by newlines
- [ ] ABA12:	Should call getUserInput with a prompt to select a contact by index number or press e for exit
- [ ] ABA13:	Should print error message where invalid input received
- [ ] ABA14:	Should recall getUserInput where invalid input received
- [ ] ABA15:	Should print top-level menu when exit selected

#### Run - Read Contact
- [ ] ABA16:	Should call printMessage with the contact's name, phone number and email address when contact selected

#### Run - Update Contact
- [ ] ABA17:	Should call getUserInput with a prompt for a contact's name, defaulting to the current name
- [ ] ABA18:	Should call getUserInput with a prompt for a contact's phone number, defaulting to the current phoneNumber
- [ ] ABA19:	Should call getUserInput with a prompt for a contact's email, defaulting to the current email address
- [ ] ABA20:	Should call printErrorMessage with an error message where an error is thrown
- [ ] ABA21:	Should update contact in contacts with correct values
- [ ] ABA22:	Should print success message with notification that user has been updated
- [ ] ABA23:	Should reprint user details
- [ ] ABA24:	Should reprint user menu

#### Run - Delete Contact
- [ ] ABA25:	Should remove contact from contacts
- [ ] ABA26:	Should print success message to inform user that contact deleted
- [ ] ABA27:	Should print top level menu options after informing user that the contact has been deleted

#### Run - Search by Name
- [ ] ABA28:	Should call printMessage with each matching contact's name, prefixed with a 1-based index number, e.g. "1. Jane Doe", "2. John Doe" and separated by newlines where multiple matches
- [ ] ABA29:	Should call printMessage with the contact's name, phone number and email address when contact selected where one match
- [ ] ABA30:	Should print warning message that no matching contacts found where no matches
- [ ] ABA31:	Should re-print top-level menu where no matches

#### Run - Search by Phone Number
- [ ] ABA32:	should return list with a size of 1 where phone number is matched to just one contact
- [ ] ABA33:	should return list containing matched contact where phone number is matched to just one contact
- [ ] ABA34:	should return an empty list where no phone number matches found
- [ ] ABA35:	should return list with a size of 2 where partial phone number is matched to two contacts
- [ ] ABA36:	should return list containing both matched contacts where partial phone number is matched to two contacts

#### Run - Search by Email Address
- [ ] ABA37:	should return list with a size of 1 where email address is matched to just one contact
- [ ] ABA38:	should return list containing matched contact where email address is matched to just one contact
- [ ] ABA39:	should return an empty list where no email address matches found
- [ ] ABA40:	should return list with a size of 2 where partial email address is matched to two contacts
- [ ] ABA41:	should return list containing both matched contacts where partial email address is matched to two contacts

#### Run - Delete All Contacts
- [ ] ABA42:	Should print warning message asking to confirm delete all contacts
- [ ] ABA43:	Should call getUserInput with a prompt to type "YES" or "NO"
- [ ] ABA44:	Should print error if invalid input received
- [ ] ABA45:	Should prompt user for input again if invalid input received
- [ ] ABA46:	Should call delete all contacts if user enters "YES"
- [ ] ABA47:	Should call success message to inform user that contact deleted
- [ ] ABA48:	Should print top-level menu after contact deleted
- [ ] ABA49:	Should not call delete all contacts if user enters "NO
- [ ] ABA50:	Should print top-level menu after user enters "NO"

## ConsoleInterface

| property | message                      | output  |
|----------|------------------------------|---------|
|          | getUserInput(@String)        | @String |
|          | printErrorMessage(@String)   | @void   |
|          | printMessage(@String)        | @void   |
|          | printSuccessMessage(@String) | @void   |
|          | printWarningMessage(@String) | @void   |

## ConsoleInterface Tests

- [ ] CI1:	Should print passed message to console
- [ ] CI2:	Should return correct userInput
- [ ] CI3:	Should print passed error message to console with red text
- [ ] CI4:	Should print passed warning message to console with orange text
- [ ] CI5:	Should print passed error message to console with green text








