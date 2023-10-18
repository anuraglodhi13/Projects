export const setBudgetEntry = (itemName, plannedAmount, actualAmount) => {
    return {
      type: 'SET_FORM_DATA',
      payload: {
        itemName,
        plannedAmount,
        actualAmount,
      },
    };
};

