# Generative AI Usage

## AI to create domain models

[Screenshot](images/gen-ai-domain-model.PNG)

## AI to generate test cases

I have grouped three requirements related to tests into a single copilot chat session. This was to allow copilot to 
build on its previous answers. 

The chat is quite long, so I will include the full text and partial screenshots; I hope that is ok:

> Q. I have an AddressBook class within a Java application. The AddressBook class is responsible for storing elements 
> of the type IImmutableContact. The AddressBook has a searchContacts method, which accepts a String as an argument and 
> returns a List of IImmutableContact elements. I need to write test cases specific to the functionality of ensuring 
> that the return value is ordered alphabetically based on the name property of each element. AddressBook can access
> the value of this property using the getName method of IImmutableContact. Test cases should be provided as an
> unordered list of simple expect...when... statements.

A: Here are the test cases for the functionality of ensuring that the return value is ordered alphabetically based on 
the `name` property of each `IImmutableContact` element:

- Expect the returned list to be sorted alphabetically when searching contacts with a term that matches multiple contacts.
- Expect the first contact in the returned list to have a name that comes first in alphabetical order when searching contacts with a term that matches multiple contacts.
- Expect the last contact in the returned list to have a name that comes last in alphabetical order when searching contacts with a term that matches multiple contacts.
- Expect the returned list to maintain alphabetical order even when new contacts are added to the address book.
- Expect the returned list to maintain alphabetical order even when existing contacts are removed from the address book.
- Expect the returned list to maintain alphabetical order even when existing contacts in the address book are updated.
- Expect the returned list to be empty when searching contacts with a term that matches no contacts.
- Expect the returned list to contain only one contact when searching contacts with a term that matches exactly one contact.
- Expect the returned list to maintain alphabetical order when the search term is case-insensitive.
- Expect the returned list to maintain alphabetical order when the search term contains leading or trailing spaces.

[Screenshot](./images/gen-ai-test-cases-1.PNG)

> Q. With reference to your last answer, are there any additional edge cases that you could be added to this list?

A. Yes, there are a few additional edge cases that could be considered for the test cases:

- Expect the returned list to maintain alphabetical order when the search term contains special characters.
- Expect the returned list to maintain alphabetical order when the search term contains numbers.
- Expect the returned list to maintain alphabetical order when the search term contains a mix of upper and lower case letters.
- Expect the returned list to maintain alphabetical order when the contacts have names with special characters.
- Expect the returned list to maintain alphabetical order when the contacts have names with numbers.
- Expect the returned list to maintain alphabetical order when the contacts have names with a mix of upper and lower case letters.
- Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces.
- Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for case sensitivity.
- Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for special characters or numbers.

[Screenshot](./images/gen-ai-test-cases-2.PNG)

> Q. I need to ensure that I am covering all edge and corner cases. Based on your last two answers, could you please
> provide a single, comprehensive list of tests including all conceivable edge and corner cases?

A. Certainly, here is a comprehensive list of test cases for the `AddressBook` class, including edge and corner cases:

1. Expect the returned list to be sorted alphabetically when searching contacts with a term that matches multiple contacts.
2. Expect the first contact in the returned list to have a name that comes first in alphabetical order when searching contacts with a term that matches multiple contacts.
3. Expect the last contact in the returned list to have a name that comes last in alphabetical order when searching contacts with a term that matches multiple contacts.
4. Expect the returned list to maintain alphabetical order even when new contacts are added to the address book.
5. Expect the returned list to maintain alphabetical order even when existing contacts are removed from the address book.
6. Expect the returned list to maintain alphabetical order even when existing contacts in the address book are updated.
7. Expect the returned list to be empty when searching contacts with a term that matches no contacts.
8. Expect the returned list to contain only one contact when searching contacts with a term that matches exactly one contact.
9. Expect the returned list to maintain alphabetical order when the search term is case-insensitive.
10. Expect the returned list to maintain alphabetical order when the search term contains leading or trailing spaces.
11. Expect the returned list to maintain alphabetical order when the search term contains special characters.
12. Expect the returned list to maintain alphabetical order when the search term contains numbers.
13. Expect the returned list to maintain alphabetical order when the search term contains a mix of upper and lower case letters.
14. Expect the returned list to maintain alphabetical order when the contacts have names with special characters.
15. Expect the returned list to maintain alphabetical order when the contacts have names with numbers.
16. Expect the returned list to maintain alphabetical order when the contacts have names with a mix of upper and lower case letters.
17. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces.
18. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for case sensitivity.
19. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for special characters or numbers.
20. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for a mix of upper and lower case letters.
21. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces and case sensitivity.
22. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces and special characters or numbers.
23. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, and special characters or numbers.
24. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, and a mix of upper and lower case letters.
25. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, and different combinations of these factors.
26. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, and different permutations of these factors.
27. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, and different variations of these factors.
28. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, and different variations of these factors in different combinations.
29. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, different variations of these factors in different combinations, and different variations of these factors in different permutations.
30. Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, different variations of these factors in different combinations, different variations of these factors in different permutations, and different variations of these factors in different variations.

[Screenshot](./images/gen-ai-test-cases-3.PNG)

## AI to generate code for test cases

Again, the response is long, so I will include the text in full and a partial screenshot. 

> Q. Considering the existing tests in the referenced file, AddressBookTest.java. I need code to implement one
> edge case and one corner case from the list of tests provided in your last answer. Please ensure that the tests are
> consistent with those in the referenced file.

A. Sure, I can provide you with the code for two test cases from the list. Let's consider the following edge and corner cases:

Edge Case: "Expect the returned list to maintain alphabetical order when the search term contains special characters."
Corner Case: "Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, different variations of these factors in different combinations, different variations of these factors in different permutations, and different variations of these factors in different variations."

Here is the code for these test cases:

```java
@DisplayName("Test Search Contacts")
@Nested
class SearchContactTests{

    @Test
    @DisplayName("Edge Case: Expect the returned list to maintain alphabetical order when the search term contains special characters.")
    public void edgeCaseTest() {
        // Arrange
        when(testContact1.getName()).thenReturn("a*bc");
        when(testContact2.getName()).thenReturn("a$bc");
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        // Act
        List<IImmutableContact> results = testAddressBook.searchContacts("a");

        // Assert
        assertEquals(2, results.size());
        assertEquals(testContact1, results.get(0));
        assertEquals(testContact2, results.get(1));
    }

    @Test
    @DisplayName("Corner Case: Expect the returned list to maintain alphabetical order when the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, different variations of these factors in different combinations, different variations of these factors in different permutations, and different variations of these factors in different variations.")
    public void cornerCaseTest() {
        // Arrange
        when(testContact1.getName()).thenReturn("  aBc1*  ");
        when(testContact2.getName()).thenReturn("  aBc2$  ");
        testAddressBook.addContact(testContact1);
        testAddressBook.addContact(testContact2);

        // Act
        List<IImmutableContact> results = testAddressBook.searchContacts("aBc");

        // Assert
        assertEquals(2, results.size());
        assertEquals(testContact1, results.get(0));
        assertEquals(testContact2, results.get(1));
    }
}
```

In the edge case test, we are testing the scenario where the search term contains special characters. We have two contacts with names "a*bc" and "a$bc". When we search for "a", both contacts should be returned in alphabetical order.

In the corner case test, we are testing the scenario where the contacts have names that are identical except for leading or trailing spaces, case sensitivity, special characters or numbers, a mix of upper and lower case letters, different combinations of these factors, different permutations of these factors, different variations of these factors, different variations of these factors in different combinations, different variations of these factors in different permutations, and different variations of these factors in different variations. We have two contacts with names "  aBc1*  " and "  aBc2$  ". When we search for "aBc", both contacts should be returned in alphabetical order.

[Screenshot](./images/gen-ai-test-cases-4.PNG)

## AI to debug, refactor and analyse the efficiency of code

I did not plan to use generative AI until I had completed both the core and additional requirements. This was because
this is my first attempt at a Java application and I enjoyed the experience. 

The one exception was when debugging a method written for the requirement that results be sorted. I was excited to write
this method as I had taken a course on sorting algorithms last year and saw this as an opportunity to apply that
knowledge. However, the code was not working properly and debugging was slow, so I asked chatGPT.

### Step 1: Debugging

[Screenshot](./images/gen-ai-debugging.PNG)

### Step 2: Efficiency Analysis and Refactoring

Here, I am hoping to use a single chat for both efficiency analysis and refactoring requirements. The question
I asked related to efficiency, but the response was mostly useful for refactoring.

The AI response was great - I think the code it provided has the same computational efficiency
as my original method - However, it is far cleaner; it is for this reason, rather than efficiency
that I decided to implement it.

The original method, as debugged, for reference:

```java
    private int getIndexAtWhichToStoreNewContact(String name){
        if(contacts.isEmpty()) return 0;
        int left = 0, right = contacts.size()-1, i;
        while(left < right){
            i = (int)(Math.floor(((double) right - (double) left)/2)) + left;
            if(contacts.get(i).getName().compareTo(name) >= 0) right = i - 1;
            else left = i + 1;
        }
        return contacts.get(left).getName().compareTo(name) > 0 ? left : left + 1;
    }
```

> Q. Can the getIndexAtWhichToStoreNewContact method in the referenced file be made more efficient?

A. The `getIndexAtWhichToStoreNewContact` method in the `AddressBook` class is using a binary search algorithm to find
the correct index to insert a new contact, ensuring that the contacts list remains sorted in alphabetical order.
This is an efficient approach with a time complexity of O(log n), where n is the number of contacts.

However, the method can be made more efficient by using Java's built-in binary search method from the `Collections`
class, which also uses a binary search algorithm. This method returns the index of the search key,
if it is contained in the list; otherwise, it returns (-(insertion point) - 1). The insertion point is defined as the
point at which the key would be inserted into the list.

Here's how you can modify the method:

```java
private int getIndexAtWhichToStoreNewContact(String name){
    IImmutableContact dummyContact = new IImmutableContact() {
        @Override
        public String getName() {
            return name;
        }

        // Implement other methods as needed
    };

    int index = Collections.binarySearch(contacts, dummyContact, Comparator.comparing(IImmutableContact::getName));

    if (index < 0) {
        index = -index - 1;
    }

    return index;
}
```

In this code, a dummy `IImmutableContact` object is created with the name to be searched. This object is used to perform the binary search on the `contacts` list. The `Comparator.comparing(IImmutableContact::getName)` is used to compare the contacts based on their names.

Please note that this approach assumes that the `IImmutableContact` interface has a method `getName()` that returns the name of the contact. If this is not the case, you will need to adjust the code accordingly.

[Screenshot](./images/gen-ai-efficiency.PNG)

The final code, based on the AI response:

```java 
    private int getIndexAtWhichToStoreNewContact(IImmutableContact contactToAdd){
        int index = Collections.binarySearch(contacts, contactToAdd, Comparator.comparing(IImmutableContact::getName));
        if (index < 0) index = -index - 1;
        return index;
    }
```

## Documentation

I have used the above method again for this requirement. This is because:
- It is relatively complicated
- I have very few functions that relate exclusively to additional requirements

I have implemented the documentation, though as a standard comment rather than a Javadoc comment
because I understand that Javadoc comments are generally used for public methods only. 

[Screenshot](./images/gen-ai-documentation.PNG)
