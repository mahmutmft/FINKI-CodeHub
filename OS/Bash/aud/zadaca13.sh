#!/bin/bash

cat fruit_prices.txt | awk '{print $3, $2, $1}'