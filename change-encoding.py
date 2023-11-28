import os

def change_encoding(root_dir, original_encoding='iso-8859-1', new_encoding='utf-8'):
    for subdir, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.htm'):
                filepath = os.path.join(subdir, file)
                # Open the file in the original encoding and read the contents
                with open(filepath, 'r', encoding=original_encoding) as f:
                    content = f.read()
                # Write the contents in the new encoding
                with open(filepath, 'w', encoding=new_encoding) as f:
                    f.write(content)
                print(f"Converted {filepath} to {new_encoding}")

# Call the function with the path of the root directory
change_encoding('.')
