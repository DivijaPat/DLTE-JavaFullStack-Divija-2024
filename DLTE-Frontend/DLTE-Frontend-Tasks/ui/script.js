 const insuranceAvailed = [
    {
        id: 1,
        InsuranceCoverage: '30000',
        InsurancePremium: '1500',
        InsuranceType: 'Life',
        InsuranceName: 'LIC',
        InsuranceBenefits: 'Lifetime risk cover',
        InsuranceLifetime: '10'
    },
    {
        id: 2,
        InsuranceCoverage: '50000',
        InsurancePremium: '2400',
        InsuranceType: 'Health',
        InsuranceName: 'Reliance',
        InsuranceBenefits: 'Accidental death cover',
        InsuranceLifetime: '5'
    },
    {
        id: 3,
        InsuranceCoverage: '60000',
        InsurancePremium: '3000',
        InsuranceType: 'Vehicle',
        InsuranceName: 'Bajaj',
        InsuranceBenefits: 'Cashless claim settlement',
        InsuranceLifetime: '3'
    }
];

function createCard(insurance) {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
        <h4>${insurance.InsuranceName} (${insurance.InsuranceType})</h4>
        <p>Coverage: $${insurance.InsuranceCoverage}</p>
        <p>Premium: $${insurance.InsurancePremium} annually</p>
        <p>Benefits: ${insurance.InsuranceBenefits}</p>
        <p>Lifetime: ${insurance.InsuranceLifetime} years</p>
    `;
    return card;
}

function filterPolicies() {
    const minCoverage = document.getElementById('minCoverage').value;
    const maxCoverage = document.getElementById('maxCoverage').value;
    const policyContainer = document.getElementById('policyContainer');
    policyContainer.innerHTML = '';

    const filteredPolicies = insuranceAvailed.filter(insurance => 
        parseInt(insurance.InsuranceCoverage) >= minCoverage &&
        parseInt(insurance.InsuranceCoverage) <= maxCoverage
    );

    filteredPolicies.forEach(insurance => {
        policyContainer.appendChild(createCard(insurance));
    });
}

function listAllPolicies() {
    const policyContainer = document.getElementById('policyContainer');
    policyContainer.innerHTML = '';
    insuranceAvailed.forEach(insurance => {
        policyContainer.appendChild(createCard(insurance));
    });
}