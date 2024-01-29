import { ethers } from "hardhat";

async function main() {
    const Voting = await ethers.getContractFactory("Voting");
    const candidateNames = ["Alice", "Bob", "Charlie"]; // Replace with actual candidate names
    const durationInMinutes = 60; // Duration in minutes

    const voting = await Voting.deploy(candidateNames, durationInMinutes);
    console.log("Deploying Voting contract...");

  
    console.log("Voting contract deployed to:", voting.address);
}

main()
    .then(() => process.exit(0))
    .catch(error => {
        console.error(error);
        process.exit(1);
    });