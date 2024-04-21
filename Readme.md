# Acceptance Criteria for API Testing

## API Call Validation

### 1. API call is successful and returns valid price.
- **Description:** The API call should be successful without any errors.
- **Criteria:** Verify that the API returns a valid price in the response.

### 2. Check the status code and status returned by the API response.
- **Description:** Verify the HTTP status code and the status message in the API response.
- **Criteria:** 
    - The API could return multiple statuses like SUCCESS, FAILURE, etc.
    - Ensure that all possible statuses are handled and validated.

## Price Range Validation

### 3. Fetch the USD price against the AED and make sure the prices are in range between 3.6 – 3.7.
- **Description:** Verify the price conversion from AED to USD.
- **Criteria:** 
    - Fetch the USD price against the AED.
    - Validate that the prices fall within the range of 3.6 – 3.7.

## Currency Pair Validation

### 4. Verify that 162 currency pairs are returned by the API.
- **Description:** Check the number of currency pairs returned by the API.
- **Criteria:** 
    - Verify that exactly 162 currency pairs are returned in the API response.

## JSON Schema Validation

### 5. Make sure API response matches the JSON schema.
- **Description:** Validate the structure and data types of the API response against a JSON schema.
- **Criteria:** 
    - Generate a schema from the API response.
    - Compare the API response against the generated schema to ensure they match.
