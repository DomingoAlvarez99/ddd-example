#!/bin/bash

function print_deps_targets() {
	local deps_targets_arr=("$@")

	if [ ${#deps_targets_arr[@]} -eq 0 ]; then
		return 0
	fi

	echo -ne "\n   ${WHITE}Depends on:"

	for dep_target in "${deps_targets_arr[@]}"; do
		echo -ne "\n    $WHITE-$CYAN make $dep_target"
	done

	echo
}

function print_targets() {
	local makefile_path=$1

	echo -ne "${WHITE}Help for $makefile_path\n"

	local targets_str=`cat "$makefile_path" | egrep -i '^[a-zA-Z_-]+:.+##.+$' | tr '\n' ';'`
	IFS=';' read -ra targets_arr <<< "$targets_str"

	for target in "${targets_arr[@]}"; do
        	local target_name=$(echo "$target" | cut -d ":" -f 1)
        	local target_description=$(echo "$target" | cut -d "#" -f 3)

        	echo -e "\n $WHITE-$GREEN$target_description"
        	echo -e "  $ORANGE make $target_name"

       		local deps_targets_str=$(echo "$target" | cut -d ":" -f 2 | cut -d "#" -f 1)
        	IFS=' ' read -ra deps_targets_arr <<< "$deps_targets_str"

		print_deps_targets "${deps_targets_arr[@]}"
	done
}

makefile_path=Makefile

print_targets $makefile_path